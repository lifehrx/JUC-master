package com.lifehrx.juc.c_020_01_3;

import java.util.concurrent.CountDownLatch;
/**
 * 注释：新的锁都用到了AQS,即都用到了CAS
 *
 * CountDownLatch : 倒数门栓（54321 门开了）等待发车坐满就走
 *
 * JDK1.8官方文档：允许一个或多个线程等待直到在其他线程中执行的一组操作完成的同步辅助。
 *                有线程调用await在门口等待，直到被调用countDown()的线程打开。
 *                一个CountDownLatch初始化N可以用来做一个线程等待，直到N个线程完成某项操作，
 *                或某些动作已经完成N次。
 */
public class T06_TestCountDownLatch {

    public static void main(String[] args) {
        //usingJoin();
        usingCountDownLatch();
    }

    /**
     * 法一：CountDownLatch
     */
    private static void usingCountDownLatch() {
        // 100 个线程
        Thread[] threads = new Thread[100];

        CountDownLatch latch = new CountDownLatch(threads.length);

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0; j<10000; j++) result += j;
                // 线程结束才会调用--（是原子的没有线程安全问题）
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            // 法一 : 门栓栓住门 （100 每个线程结束就减1）== 0 时门就打开了
            // 用于阻塞的，不用谈及线程安全
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

    /**
     * 法二：Join
     */
    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0; j<10000; j++) result += j;
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                // 法二：线程结束用Join(). 不是很好控制不灵活
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");
    }
}
