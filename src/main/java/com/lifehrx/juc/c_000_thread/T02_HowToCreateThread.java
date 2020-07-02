package com.lifehrx.juc.c_000_thread;

/**
 * 启动线程的3种方法
 *  1：Thread
 *  2: Runnable
 *  3: Executors.newCachedThread 通过线程池启动
 *  4.或 Lamda表达式
 *
 *  CPU:是一个很土的东西，一个线程在上面执行一会儿，然后被扔出去排队，让给别人
 */
public class T02_HowToCreateThread {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }

    public static void main(String[] args) {
        // 法一：
        new MyThread().start();
        // 法二：
        new Thread(new MyRun()).start();
        // 法三：
        new Thread(()->{
            System.out.println("Hello Lambda!");
        }).start();

    }

}

//请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThread
