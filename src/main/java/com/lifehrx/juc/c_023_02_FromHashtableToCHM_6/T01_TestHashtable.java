package com.lifehrx.juc.c_023_02_FromHashtableToCHM_6;

import java.util.Hashtable;
import java.util.UUID;

/**
 * 本地代码是模拟以下情形
 *
 * 100万个数据 ，每个线程等量的取一万个 然后插入到Hashtable里面
 */
public class T01_TestHashtable {

    static Hashtable<UUID, UUID> m = new Hashtable<>();

    //=========================用之前把线程先生成好了==============================================
    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }
    //==================原因是：写测试用例是一样的（否则每一次都重新生成实际上是不一样的）==============

    // 40:00
    static class MyThread extends Thread {

        //
        int start;
        // 100 个线程装1万个数 ： gap = 1个线程负责装多少数
        int gap = count/THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            // 从0开始也是传1万个 从1开始也是传1万个
            for(int i=start; i<start+gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        // 线程数组：里面有100个线程
        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i=0; i<threads.length; i++) {
            // 每个线程起始值不同:0-1万 1万01 - 2万 (每个线程记录下来“负责”哪些东西)
            threads[i] =
            new MyThread(i * (count/THREAD_COUNT));
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Hashtable 插入所用时间 = " + (end - start) + " 毫秒");

        System.out.println("Hashtable 插入的线程个数" + m.size() + " (保证是100万个)");

        //--------------- 读 （50:20视频时间） --------------------

        start = System.currentTimeMillis();
        // 启动100个线程，读1000万次的第10个元素
        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println("Hashtable读 " + (end - start) + " 毫秒");
    }
}
