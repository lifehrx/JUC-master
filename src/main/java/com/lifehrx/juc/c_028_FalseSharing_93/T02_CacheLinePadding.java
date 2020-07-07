package com.lifehrx.juc.c_028_FalseSharing_93;

/**
 * 写法二 ： 不在同一缓存行
 * 模拟：两个线程同时在更新各自数组里x的值。
 *       不在同一个缓存行。
 *       效率比T01高，时间少了。
 */
public class T02_CacheLinePadding {
    // 7*8 = 56 字节 这7个变量是为了缓存行对齐，填充，效率提升了
    private static class Padding {
        // 空间虽然浪费了一点，但是效率提升了。
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    // 继承Padding ： T 对象自己占一行，所以不会和其他占一行
    private static class T extends Padding {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

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
        System.out.println("不在同一缓存行所用时间：" + (System.nanoTime() - start)/100_0000);
    }
}
