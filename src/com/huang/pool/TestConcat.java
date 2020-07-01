package com.huang.pool;

//直接使用双引号声明出来的 String 对象会直接存储在常量池中。双引号，双引号，双引号
public class TestConcat {
    public static void main(String[] args) {
        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";  //在常量池
        String str4 = str1 + str2;    //在堆 在堆 在堆 在堆注意！！！
        String str5 = "string";       //在常量池

        System.out.println(str3 == str4);  //false
        System.out.println(str3 == str5);  //true
        System.out.println(str4 == str5);  //false
    }
}
