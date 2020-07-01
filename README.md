# JVMStudy
Some examples to help understanding JVM


## 1. JVM体系结构

![JVM体系结构](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/1.png?e=1593595330&token=eLO5LQ03FQToHRjQtVmH-MLTQpW_QzBp1ONAMojq:6-r5BScfNYHxcorYJ1-CEax9giM=)
 
 所谓JVM调优，基本上调的就是堆。（方法区是特殊的堆,JDK1.8之后在元空间里）
 
## 2.  类加载器ClassLoader
#### 类的加载、连接、初始化
![加载过程](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/%E5%8A%A0%E8%BD%BD%E8%BF%87%E7%A8%8B.png?e=1593595330&token=eLO5LQ03FQToHRjQtVmH-MLTQpW_QzBp1ONAMojq:py3euSK0rhCOeOklNBwohFB9cFM=)
 - 加载：查找并加载类的二进制数据
 
 - 连接：验证、准备、解析
  　1. 验证：确保被加载的类的正确性
  　2. 准备：为类的静态变量分配内存并赋**默认值**
  　3. 解析：把类中的符号引用转换为直接引用
  　   
> 在编译的时候每个Java类都会被编译成一个class文件，但在编译的时候虚拟机并不知道所引用类的位置，就用符号引用来代替。而在这个解析阶段就是为了把这个符号引用转化成为真正的地址的阶段。

   
 - 初始化：为类的静态变量赋予**正确的初始值**


#### ClassLoader分类
![ClassLoader分类](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/1.png?e=1593595330&token=eLO5LQ03FQToHRjQtVmH-MLTQpW_QzBp1ONAMojq:6-r5BScfNYHxcorYJ1-CEax9giM=)

##### 双亲委派机制（Parents Delegation Model）
类加载器收到类加载的请求时，会将请求向上委托，直到启动类加载器（BootClassLoader），启动类加载器检查是否能加载这个类，能加载就结束。
如果不能加载，就去扩展类加载器（ExtClassLoader），扩展类加载也检查是否能加载这个类，能加载就退出，不能就再去应用程序类加载器。
应用程序类加载器（AppClassLoader）检查是否能加载，能就结束，不能就报异常。

作用：从最内层JVM自带类加载器开始加载，外层恶意同名类得不到加载从而无法使用。防止恶意代码干涉正常的代码。

## 3.  Native关键字
看Native.java

## 4.  程序计数器（Program Counter Register）

 - **线程私有**，只想方法区中的方法字节码。
 - 比如在执行引擎，每一次要读取下一条指令时+1
 - 是一个非常小的内存，可以忽略不计

## 5. 方法区（Method Area）
被所有**线程共享**！

所有定义方法的信息都保留在这个区域

 - 静态变量、方法、运行时常量池、类信息（也就是类模板）
 - ==也就是static、final、运行时常量池、Class #F44336==

特别注意：这里是**运行时常量池**，不包括字符串常量池！字符串常量池在JDK1.7被单独拿到了堆里
## 6. 栈 (Stack)
栈管理程序运行

生命周期与线程同步：一旦线程结束，栈内存也就释放 -->不存在GC

存：**八大基本类型**（byte, short, int, long, float, double, char, boolean），**对象引用**

栈的运行原理：栈中的数据以**栈帧**的形式存在。每个方法执行的同时就会创建一个栈帧-->每个方法执行到结束的过程对应着栈帧在JVM中入栈到出栈的过程

![栈帧](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/%E6%A0%88%E5%B8%A7.png)

程序正在执行的方法，一定在栈顶

如果无限套娃导致栈满了，就会出现栈溢出：StackOverflowError

``` java
public class MatryoshkaDoll {
    public static void main(String[] args) {
        stack1();
    }
    public static void stack1(){
        stack2();
    }
    public static void stack2(){
        stack1();
    }
}
```

## 7. 堆（Heap）
一个JVM只有一个堆内存，并且堆内存的大小可以调节。

new产生的所有对象实例都在堆内存。

堆内存分成三部分：新生区，养老代，~~永久存储区~~（JDK1.8之后是 **元空间**）
![堆内存逻辑](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/%E5%A0%86%E5%86%85%E5%AD%98%E9%80%BB%E8%BE%91.png)


GC主要是在新生区和老年区，又分为**轻GC** 和 **重GC**，如果内存不够or存在死循环，就会导致 OOM错误

![堆结构](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/%E5%A0%86%E7%BB%93%E6%9E%84.png)

注意！方法区 也叫 非堆，**元空间 是 方法区 的实现**！

《Java 虚拟机规范》只是规定了有方法区这么个概念和它的作用，并没有规定如何去实现它。那么，在不同的 JVM 上方法区的实现肯定是不同的了。**方法区和~~永久代~~元空间的关系很像 Java 中接口和类的关系，类实现了接口**，而~~永久代~~元空间就是 HotSpot 虚拟机对虚拟机规范中方法区的一种实现方式。方法区是 Java 虚拟机规范中的定义，是一种规范，而~~永久代~~元空间是一种实现，**一个是标准一个是实现**。

元空间与堆不相连，但与堆共享物理内存，只是逻辑上在堆中。不存在GC，关闭JVM就会释放元空间的内存。

元空间(Metaspace)鸠占鹊巢，方法区存在于元空间(Metaspace)。同时，JDK1.8元空间不再与堆连续，而且是存在于本地内存（Native memory）

#### 堆内存调优

> -Xms1024m -Xmx1024m -XX:+PrintGCDetails

-Xms:设置初始分配大小，默认为物理内存的1/64
-Xmx:最大分配内存，默认为物理内存的1/4
-XX:+PrintGCDetails:输出详细的GC处理日志

VM参数调优，把JVM最大内存和初始内存都调为1024M，运行查看结果。

![VM参数调优](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/VM%E5%8F%82%E6%95%B0%E8%B0%83%E4%BC%98.png)

PSYoungGen      total 305664K +  ParOldGen       total 699392K = 1005056/1024 =981.5MB

也就证明了元空间并不在虚拟机，而是使用本地内存。==>元空间逻辑上存在于堆内存，但物理上不存在于堆内存

#### TestOOM
```java
public class TestOOM {
    public static void main(String[] args) {
        String s = "NMSL";

        while(true){
            s += new Random().nextInt(888888888) + new Random().nextInt(999999999);
        }
    }
}
```

VM options调成

> -Xms1m -Xmx1m -XX:+PrintGCDetails

![结果1](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/GC1.png)
![结果2](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/GC2.png)

轻GC只清理新生代的内存，重GC清理新生代和老年代 -->直到重GC清理了新生代，老年代还是内存不够，就报错OOM

如何排查OOM错误？

 1. 尝试扩大堆内存查看结果
 2. 分析内存，看哪个地方出错（使用内存快照分析工具Jprofiler）

#### 导出Dump文件

> -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError

在heap中如果爆OOM就Dump出这个文件。举一反三，后面的错误可以自己写别的

运行结果: Dumping heap to java_pid123456.hprof ...

然后就可以用Jprofiler查看里面**Biggest Objects 大的对象**

## 8.GC

GC的作用域：**堆，方法区**

次数频繁young区，次数较少old区，基本不动方法区

JVM在进行GC时，并非每次都对上面三个内存区域一起回收的，大部分回收的是新生代。

因此GC按照回收的区域又分了两种类型：普通GC（Minor GC），全局GC(Full GC)

Minor GC:只针对新生代区域的GC
Full GC:针对老年代的GC，偶尔伴随对新生代的GC以及对方法区的GC

#### 引用计数法
![引用计数法](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/%E5%BC%95%E7%94%A8%E8%AE%A1%E6%95%B0%E6%B3%95.png)

在对象头分配一点空间，记录对象被引用的次数，引用次数为0时就回收。

缺点：

 - 每次对对象赋值时都要维护引用计数器，且计数器本身也有一定的消耗
 - 较难处理循环引用

JVM的实现一般不采用这种方式

#### 复制算法
==年轻代 #F44336==中使用的Minor GC 就是 复制算法（Copying）

每次GC后，会把**伊甸园区的对象和幸存from区的对象**移动到**幸存to区**（注意！此时，有幸存对象的那个区就变成了幸存from区，没对象的那个幸存区就变成了幸存to区  -->**==谁空谁是to #F44336==**）

幸存to区、幸存from区是动态交替的。==**每经历一次GC，伊甸园区和幸存to区都会变成空的** #F44336==

![复制算法](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/%E5%A4%8D%E5%88%B6%E7%AE%97%E6%B3%95.png)

年轻代分成三个部分：一个Eden区和2个Survivor区（from区，to区）。默认比例8:1:1
因为年轻代的对象基本上都是朝生夕死，所以年轻代的垃圾算法使用的是复制算法。复制算法的思想就是将内存分成两块，每次只用一块，当这一块内存用完，就将还活着的对象复制到另外一块上面。复制算法不会产生内存碎片。

可以通过 -XX:MaxTenuringThreshold=15 来修改进入老年代要经历的GC的次数

优点：没有内存碎片

缺点：浪费内存空间（空了一块内存）
假设极端情况，对象的存活率为100%，复制算法就会把所有对象都复制一遍，并将所有引用地址重置一边。复制 这一工作所花费的时间，在对象存活率到达一定程度时将变得不可忽视。

所以要想使用复制算法，最起码对象的存活率要非常低。-->所以应用在新生代

#### 标记清除算法
需要扫描**两次**

![标记清除算法](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/%E6%A0%87%E8%AE%B0%E6%B8%85%E9%99%A4.png)

标记：从引用根节点开始标记所有被引用的对象，标记的过程其实就是遍历所有的GC Roots，然后将所有GC Roots可达的对象，标记为存活的对象。

清除：遍历整个堆，把未标记的对象清除。

优点：不需要额外空间
缺点：扫描两次，耗时；产生内存碎片

#### 标记压缩算法
![标记压缩算法](http://qcorkht4q.bkt.clouddn.com/%E5%B0%8F%E4%B9%A6%E5%8C%A0/%E6%A0%87%E8%AE%B0%E5%8E%8B%E7%BC%A9.png)

在压缩阶段，不再对标记的对象作回收，而是通过所有存活对象都向一端移动，然后直接清除边界以外的内存。可以看到，标记的存活对象将会被整理，按照内存地址依次排列，而未被标记的内存会被清理掉。

优点：没有内存碎片
缺点：需要移动对象的成本（扫描三次）

#### 标记-清除-压缩算法（Mark-Sweep-Compact）

原理：

 1. 标记清除 + 标记压缩
 2. 和标记清除一致，**==当进行多次GC后才压缩 #F44336==**，减少移动对象的成本
   
#### GC算法总结
内存效率（时间复杂度，简单理解就是**需要遍历的次数**）：复制算法 > 标记清除算法 > 标记压缩算法

内存整齐度：标记清除算法 = 标记压缩算法 >  复制算法 （因为复制算法浪费了一块区域）

内存利用率：复制算法 = 标记压缩算法 > 标记清除算法 （只有标记清除会产生内存碎片）

--> 没有最好，只有更好

新生代：对象存活率低 ---->复制算法
老年代：标记-清除-压缩算法 ---> 混合实现，**调优就可以调 标记清除 多少次之后，才进行一次压缩**

=====》所以GC也称为  ==分代收集算法 #F44336==

## 9. 字符串常量池

**字符串常量池在堆，运行时常量池在方法区**（元空间）

Q：String s1 = new String("abc");这句话创建了几个字符串对象？

A：1 or 2个。
如果池中已存在字符串常量“abc”，则只会在堆空间创建一个字符串常量“abc”。如果池中没有字符串常量“abc”，那么它将**首先在池中创建**，然后在堆空间中创建，因此将创建总共 2 个字符串对象。

```java
//先检查字符串常量池中有没有"abcd"，如果字符串常量池中没有，则创建一个，然后 str1 指向字符串常量池中的对象，如果有，则直接将 str1 指向"abcd""；
String str1 = "abcd"; 
 //堆中创建一个新的对象
String str2 = new String("abcd");  
```
参考TestConcat，TestIntern。

只要使用 new 方法，便需要创建新的对象。使用双引号就会直接存在字符串常量池。

## 基本类型包装类的常量池
**Byte,Short,Integer,Long**,Character,Boolean

前面 4 种包装类默认创建了**数值[-128，127]** 的相应类型的缓存数据，Character创建了数值在[0,127]范围的缓存数据，Boolean 直接返回True Or False。如果超出对应范围仍然会去创建新的对象。

TestInteger
```java
public class TestInteger {
    public static void main(String[] args) {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2   " + (i1 == i2));            //true
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));    //true
        System.out.println("i1=i4   " + (i1 == i4));            //false
        System.out.println("i4=i5   " + (i4 == i5));            //false
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));    //true
        System.out.println("40=i5+i6   " + (40 == i5 + i6));    //true

        /*语句 i4 == i5 + i6，因为+这个操作符不适用于 Integer 对象，首先 i5 和 i6 进行自动拆箱操作，进行数值相加，即 i4 == 40。
        然后 Integer 对象无法与数值进行直接比较，所以 i4 自动拆箱转为 int 值 40，最终这条语句转为 40 == 40 进行数值比较。*/
    }
}
```

还有一个问题没找到答案，这些包装类型的常量池在哪？方法区吗？不懂