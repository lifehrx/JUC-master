package com.lifehrx.juc.c_020_01_3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport : 锁的支持
 *
 * 控制某一个线程：的阻塞
 *
 * unpark()可以用在 park()之前 ,但是wait()和notify() 不可以
 *
 * 底层就是维护一个count
 */
public class T13_TestLockSupport {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i == 5) {
                    // 当前线程停止
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        // t 线程开始 （叫醒）
        LockSupport.unpark(t);

       /* try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 8 senconds!");

        LockSupport.unpark(t);*/

    }
}
