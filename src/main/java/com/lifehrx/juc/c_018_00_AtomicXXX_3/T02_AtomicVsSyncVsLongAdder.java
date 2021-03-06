package com.lifehrx.juc.c_018_00_AtomicXXX_3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 第3课 : 23:00:00
 * <p>
 * 比较 sync AtomicLong LongAdder 效率
 */
public class T02_AtomicVsSyncVsLongAdder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    /**
     * 1000 个线程做 10万次递增
     */
    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[1000];

        // ------------------------ 一 ： Atomic -----------------------------------
        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int k = 0; k < 10_0000; k++) count1.incrementAndGet();
                    });
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        //TimeUnit.SECONDS.sleep(10);

        System.out.println("Atomic: " + count1.get() + " 用时 = " + (end - start));

        //------------------------- 二: synchronized ----------------------------------
        Object lock = new Object();

        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            for (int k = 0; k < 10_0000; k++)
                                synchronized (lock) {
                                    count2++;
                                }
                        }
                    });
        }

        start = System.currentTimeMillis();

        for (Thread t : threads) t.start();

        for (Thread t : threads) t.join();

        end = System.currentTimeMillis();


        System.out.println("Sync: " + count2 + " 用时 = " + (end - start));


        //-----------------------三 ： LongAdder 分段锁----------------------------
        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int k = 0; k < 10_0000; k++) count3.increment();
                    });
        }

        start = System.currentTimeMillis();

        for (Thread t : threads) t.start();

        for (Thread t : threads) t.join();

        end = System.currentTimeMillis();

        //TimeUnit.SECONDS.sleep(10);

        System.out.println("LongAdder: " + count1.longValue() + " 用时 = " + (end - start));

    }

    static void microSleep(int m) {
        try {
            TimeUnit.MICROSECONDS.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
