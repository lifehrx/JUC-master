package com.lifehrx.juc.c_000_thread;

import java.util.concurrent.TimeUnit;

/**
 * 1.什么是线程
 *
 * 一个程序如.exe 运行起来之后是线程
 *
 * 线程是一个程序里不同的执行路径
 */
public class T01_WhatIsThread {

    /**
     * 启动线程 法一： extends Thread
     */
    private static class T1 extends Thread {

        @Override
        public void run() {
           for(int i=0; i<10; i++) {
               try {
                   TimeUnit.MICROSECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("T1");
           }
        }
    }

    public static void main(String[] args) {
        /**
         * 输出结果：T1....... main........
         * 方法调用法
         */
        //new T1().run();

        /**
         * 输出结果：T1 main T1 main main T1
         * 线程分支
         * 当调用 new T1().start(); 会产生一个分支T1,并且和主程序同步运行
         */
        new T1().start();

        for(int i=0; i<10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }

    }
}
