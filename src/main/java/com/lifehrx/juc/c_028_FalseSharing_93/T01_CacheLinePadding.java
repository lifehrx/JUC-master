package com.lifehrx.juc.c_028_FalseSharing_93;

/**
 * 写法一 ： 模拟在同一缓存行
 * 模拟：两个线程同时在更新各自数组里x的值。
 *      两个x在同一个缓存行内被改变值。
 *
 */
public class T01_CacheLinePadding {

    // Long 类型 ： 8 字节
    private static class T {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    // 内存里面new出来两个 Long 类型的数组, 数组1,2位于同一个缓存行
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {

        // 线程1：循环100万次，一直在改变x的值
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        // 线程2：循环100万次，同样是一直在改变x的值
        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("在同一缓存行所用时间 ： " + (System.nanoTime() - start)/100_0000);
    }
}
