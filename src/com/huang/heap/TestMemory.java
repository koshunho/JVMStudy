package com.huang.heap;

public class TestMemory {
    public static void main(String[] args) {
        //返回JVM试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();

        //返回JVM的初始化总内存
        long totalMemory = Runtime.getRuntime().totalMemory();

        //默认JVM试图使用最大内存是电脑内存的1/4，初始化总内存为电脑内存的1/64
        System.out.println("JVM试图使用最大内存 = " + maxMemory + "(K)-->" + (maxMemory / (double) 1024 / 1024) + "MB");
        System.out.println("JVM初始化总内存 = " + totalMemory + "(K)-->" + (totalMemory / (double) 1024 / 1024) + "MB");
    }
}
