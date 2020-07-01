package com.huang.classloader;

//调用类并没有直接用到定义常量的类，因为Parent2这个类的常量在编译阶段就会存入TestStatic2的常量池中，并没有触发Parent2的初始化
public class TestStatic2 {
    public static void main(String[] args) {
        /*输出结果：
        * I am Father*/
        System.out.println(Parent2.str);
    }
}


class Parent2{
    public static final String str = "I am Father";

    static {
        System.out.println("Parent static");  //这一行不会输出
    }
}