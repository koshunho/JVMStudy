/*
package java.lang;
*/
/*
* 错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
   public static void main(String[] args)
否则 JavaFX 应用程序类必须扩展javafx.application.Application*//*


// 引申出双亲委派机制：APP->EXT----->BOOT(最终执行)
// 如果Boot找不到就往Ext找，Ext找不到就往App找
// 因为我们自己写的这个，会在BOOT中找到，就不会执行我们自己写的。所以说找不到main方法

*/
/*
* 1，类加载器收到类加载的请求
* 2.将这个请求向上委托给父类加载器去完成，一直向上委托，直到启动类BOOT加载器
* 3.启动加载器检查是否能加载当前这个类（使用findClass()方法），能就加载（结束）；否则，抛出异常，通知子加载器进行加载
* 4.重复3
*
* 双亲委派机制可以确保Java核心类库所提供的类，不会被自定义的类所替代。*//*

public class String {
    public String toString(){
        return "NMSL";
    }

    public static void main(String[] args) {
        String s = new String();
        s.toString();
    }
}
*/
