package com.lifehrx.juc.c_005_synchronize_run;

public class TestDemo implements Runnable {

    private volatile int count = 10;


    @Override
    public synchronized void run() {
        count--;

        System.out.println(Thread.currentThread().getName() + "count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();

        for (int i = 0; i < 100; i++) {
            new Thread(t, "thread = ").start();
        }
    }
}
