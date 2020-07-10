package com.lifehrx.juc.c_020_01_3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier : 循环栅栏 （人满了就把它推到）
 *
 * 19 个线程在等着因为第20个满了就发车了 （100/20 = 5车）
 *
 * 应用场景1：限流 （太多数据一下打到数据库这样的情景，分批访问数据库，本例子就是每20个访问数据）
 * 但实际中使用 ： Guava RateLimiter
 *
 * 应用场景2： 复制操作访问文件，数据库， 网络。并发的执行3种操作等都返回了相应的数据，再进行下一步操作。
 */
public class T07_TestCyclicBarrier {

    public static void main(String[] args) {
        // 满20人发车 （两种new方法）
        /**
         * 写法一 ：
         */
        //CyclicBarrier barrier = new CyclicBarrier(20);

        /**
         * 写法二 ： 2个参数
         */
        //CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人"));

        /**
         * 写法三：比较优雅的写法 2个参数
         */

        CyclicBarrier barrier = new CyclicBarrier(1, new Runnable() {
            int i = 0;

            @Override
            public void run() {
                System.out.println(++i + " 满人，发车");
            }
        });


        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                try {

                    // 第1....n 个线程来了在 barrier栅栏这儿等着（等够20个了发车）每个线程必须调用await() ，要不栏不住。
                    barrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
