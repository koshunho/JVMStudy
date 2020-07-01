package com.huang.classloader;

import java.util.UUID;

// 当一个常量的值并非编译阶段可以确定，那么其值就不会被放到调用类的常量池中
// 这时在程序运行时，会导致主动使用这个常量所在的类，显然就会导致这个类被初始化。
// 跟TestStatic2比较，因为TestStatic3是运行期才能确定值，而TestStatic2是编译时就能确定的值
public class TestStatic3 {
    public static void main(String[] args) {
        /*输出结果：
        * Parent static
          48eb37b1-5fe0-43dd-9cc6-adee22e9ae21*/
        System.out.println(Parent3.str);
    }
}
class Parent3{
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("Parent static");  //会输出
    }
}
