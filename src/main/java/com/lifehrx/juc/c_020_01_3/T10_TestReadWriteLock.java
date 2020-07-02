package com.lifehrx.juc.c_020_01_3;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁:必考
 * 应用场景：读时候和写时候都要上锁，否则读的时候还有线程写入，读到了脏数据
 *
 * 共享锁(读锁) :
 *
 * 排他锁(写锁) :
 *
 * 但是实际用优先sync
 */
public class T10_TestReadWriteLock {

    /**
     * 法一：读写共用一把锁
     * 一个一个的读
     * 用时间：20s
     */
    static Lock lock = new ReentrantLock();
    private static int value;

    /**
     * 法二 : 两把锁
     * 读用读的锁，写用写的锁
     * 用时间：1s
     */
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    /**
     * read(Lock lock)
     * @param lock 读的时候传进去一把锁
     */
    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println(" read over!");
            //模拟读取操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 写的时候也传进去一把锁
     * @param lock
     * @param v
     */
    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            // 每个线程1s
            Thread.sleep(1000);
            value = v;
            System.out.println(" write over!");
            //模拟写操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        // 法一：
        //Runnable readR = ()-> read(lock);
        //Runnable writeR = ()->write(lock, new Random().nextInt());

        // 法二 ：
        Runnable readR = ()-> read(readLock);
        Runnable writeR = ()->write(writeLock, new Random().nextInt());

        // 启 18 个读线程
        for(int i=0; i<18; i++) new Thread(readR).start();
        // 启 2 个写线程
        for(int i=0; i<2; i++) new Thread(writeR).start();
    }
}
