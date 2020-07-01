package com.huang.classloader;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        //类是模板，对象是具体的

        ClassLoaderDemo demo1 = new ClassLoaderDemo();
        ClassLoaderDemo demo2 = new ClassLoaderDemo();
        ClassLoaderDemo demo3 = new ClassLoaderDemo();

        System.out.println(demo1.hashCode());  //1163157884
        System.out.println(demo2.hashCode());  //1956725890
        System.out.println(demo3.hashCode());  //356573597

        Class<? extends ClassLoaderDemo> aClass1 = demo1.getClass();
        Class<? extends ClassLoaderDemo> aClass2 = demo2.getClass();
        Class<? extends ClassLoaderDemo> aClass3 = demo3.getClass();


        System.out.println(aClass1.hashCode());  //460141958
        System.out.println(aClass2.hashCode());  //460141958
        System.out.println(aClass3.hashCode());  //460141958

        ClassLoader classLoader = aClass1.getClassLoader();
        System.out.println(classLoader);  //AppClassLoader --> 因为这个类，BOOT没有，EXT也没有，最后才在App找到
        System.out.println(classLoader.getParent());  //ExtClassLoader  \jre\lib\ext
        System.out.println(classLoader.getParent().getParent());  //null  在\jre\lib\rt.jar
    }
}
