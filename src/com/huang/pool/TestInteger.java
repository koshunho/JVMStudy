package com.huang.pool;

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
