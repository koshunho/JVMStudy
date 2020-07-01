package com.huang.classloader;

// 当一个类在初始化时，要求其父类全部已经初始化完毕了
// 所有Java虚拟机实现必须在每个类or接口被Java程序“首次主动使用”才初始化他们
public class TestStatic1 {
    public static void main(String[] args) {
        /*
        * 输出结果：
        * Parent static
          Child static
          I am son*/
        System.out.println(Child1.str2);
    }
}

class Parent1{
    public static String str = "I am Father";

    static {
        System.out.println("Parent static");
    }
}

class Child1 extends Parent1 {
    public static String str2 = "I am son";

    static {
        System.out.println("Child static");
    }
}
