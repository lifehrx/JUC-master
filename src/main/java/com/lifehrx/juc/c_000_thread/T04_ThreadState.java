package com.lifehrx.juc.c_000_thread;

/**
 * 低频
 * Thread:常见状态（有的可以用代码模拟出来有的不可以）
 * getState()
 *
 * 看PPT截图 ：1. 多线程与高并发(有道云笔记)
 *
 * 不能让线程关闭stop()不建议用 : 让线程正常运行结束，强制停止会造成状态不一致。
 * interpret() 不要用它来控制工程代码 ： 除非一个下线程sleep() 很久
 *
 */
public class T04_ThreadState {

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("线程状态：" + this.getState());

            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();

        System.out.println("线程状态：" + t.getState());

        t.start();
        System.out.println("线程状态：" + t.getState());

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程状态：" + t.getState());

    }
}
