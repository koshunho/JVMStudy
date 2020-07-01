package com.huang.heap;

import java.util.Random;

public class TestOOM {
    public static void main(String[] args) {
        String s = "NMSL";

        while(true){
            s += new Random().nextInt(888888888) + new Random().nextInt(999999999);
        }
    }
}
