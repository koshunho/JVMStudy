package com.huang.pool;

public class TestIntern {
    public static void main(String[] args) {
        String s1 = new String("NMSL");
        String s2 = s1.intern(); //String.intern() 是一个 Native 方法，它的作用是：如果运行时常量池中已经包含一个等于此 String 对象内容的字符串，则返回常量池中该字符串的引用；如果没有，JDK1.7之前（不包含1.7）的处理方式是在常量池中创建与此 String 内容相同的字符串，并返回常量池中创建的字符串的引用，JDK1.7以及之后的处理方式是在常量池中记录此字符串的引用，并返回该引用。
        String s3 = "NMSL";

        System.out.println(s2);        //NMSL
        System.out.println(s1 == s2);  //false    因为s1在堆，s2在常量池
        System.out.println(s3 == s2);  //true     s2和s3都在常量池
    }
}
