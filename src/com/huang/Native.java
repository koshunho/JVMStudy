package com.huang;

public class Native {
    public static void main(String[] args) {
        new Thread(()->{}).start();

        //点进去看start()，发现有一个奇怪的private native void start0()。Thread是一个普通的类，既不是抽象类又不是接口，看起来很诡异
        //凡是带了native关键字的，说明java的作用范围达不到，去调用底层C语言的库！
        //凡是带了native关键字的方法就会进入本地方法栈（本地方法栈是用来登记native方法的）
        //然后本地方法栈会调用JNI：Java Native Interface
        //本地接口的作用是融合不同的编程语言为Java所用，它的初衷是融合C/C++。Java在诞生的时候是C/C++横行的时候，想要立足，就必须有调用C/C++的程序
        //浴室就在内存中专门开辟了一块区域处理标记为native的代码，它的具体做法是在 本地方法栈 中登记native方法，在 执行引擎 执行的时候加载Native Libraries
        /*public synchronized void start() {
            if (threadStatus != 0)
                throw new IllegalThreadStateException();
            group.add(this);

            boolean started = false;
            try {
                start0();
                started = true;
            } finally {
                try {
                    if (!started) {
                        group.threadStartFailed(this);
                    }
                } catch (Throwable ignore) {
                }
            }
        }

        private native void start0();*/
    }
}
