package com.lifehrx.juc.c_020_01_3;

import java.util.concurrent.Exchanger;

/**
 * 低频
 *
 * Exchanger 交换器 ： 只能2线程
 *
 * 线程间交换数据：
 * 如果一个线程交换了 ——> 等着线程2 ——> 第二个线程来了交换 （没交互就只能exchange(s)阻塞）
 * 2个线程都执行到了 s = exchanger.exchange(s); 的时候
 * 都拿出来s放在exchanger里面，然后交换
 *
 * 不要想3个线程
 *
 * 应用场景：游戏中两个玩家交换装备
 */
public class T12_TestExchanger {

    /**
     * 容器 exchanger
     */
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        /**
         * 开始 s = "T1"
         * 结束 s = "T2"
         */
        new Thread(()->{
            String s = "T1";
            try {
                // 阻塞的
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                // 自行处理：如打断这个线程
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t1").start();



        new Thread(()->{
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t2").start();


    }
}
