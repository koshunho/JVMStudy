package com.huang.pool;

public class TestIntern {
    public static void main(String[] args) {
        String s1 = new String("NMSL");
        String s2 = s1.intern(); //String.intern() 是一个 Native 方法，它的作用是：使用intern方法的时候，会去常量池中判断是否存在这个字符串对象，如果不存在，就往常量池中存储一份，然后返回池中对应的对象。如果存在了，那么直接返回池中存在的字符串对象。
        String s3 = "NMSL";

        System.out.println(s2);        //NMSL
        System.out.println(s1 == s2);  //false    因为s1在堆，s2在常量池
        System.out.println(s3 == s2);  //true     s2和s3都在常量池
    }
}
