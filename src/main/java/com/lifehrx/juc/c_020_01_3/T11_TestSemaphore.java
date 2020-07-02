package com.lifehrx.juc.c_020_01_3;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 灯塔信号灯 : 灯亮的时候执行，不亮的时候不执行
 *
 * permits : 允许有几个信号灯
 * fair : 默认true 即公平的（在队列在等着）
 *
 * 应用场景：限流（最多的时候允许多少个线程允许）
 * 买票时候最多有5个窗口即最多5个人
 * 这个线程想继续要向下执行，必须得到Semaphore的许可
 * 收费站：8个车道谁抢到了acquire，谁先过（fair = true 不超车大家都排队等着）
 * 可以控制先来后到的公平才有意义
 * 队列AQS
 */
public class T11_TestSemaphore {

    public static void main(String[] args) {

        /**
         * 法一 ：
         */
        //Semaphore s = new Semaphore(2);
        //允许一个线程同时执行
        //Semaphore s = new Semaphore(1);

        /**
         * 法二 ：  permits： 有100个但同时能acquire到的，即同时运行的只有2个
         */
        Semaphore s = new Semaphore(1, true);



        new Thread(()->{
            try {
                // acquire 取得 ： 减一 1-1=0
                s.acquire();

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // release 释放 ：变回1
                s.release();
            }
        }).start();


        new Thread(()->{
            try {

                s.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
