package com.lifehrx.juc.c_026_00_interview_7.A1B2C3;

/**
 * 解法
 *
 * notify();
 * wait();
 */
public class T06_00_sync_wait_notify_2 {
    public static void main(String[] args) {
        // 锁对象
        final Object o = new Object();

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{
            synchronized (o) {
                for(char c : aI) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait(); //让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify(); //必须，否则无法停止程序
            }

        }, "t1").start();

        new Thread(()->{
            synchronized (o) {
                for(char c : aC) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();
            }
        }, "t2").start();
    }
}

//如果我想保证t2在t1之前打印，也就是说保证首先输出的是A而不是1，这个时候该如何做？
