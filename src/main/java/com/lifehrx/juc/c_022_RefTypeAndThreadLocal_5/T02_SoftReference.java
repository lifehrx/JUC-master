/**
 * 软引用
 *
 * 软引用是用来描述一些还有用但并非必须的对象。
 * 对于软引用关联着的对象，在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围进行第二次回收。
 * 如果这次回收还没有足够的内存，才会抛出内存溢出异常。
 * -Xms20M -Xmx20M
 *
 * 堆内存最大20 兆
 */
package com.lifehrx.juc.c_022_RefTypeAndThreadLocal_5;

import java.lang.ref.SoftReference;

public class T02_SoftReference {
    public static void main(String[] args) {

        // 10 兆
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        //m = null;
        // 拿到 字节数组的值
        System.out.println(m.get());
        // m被回收了
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        //再次分配一个数组(15兆):heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());
    }
}

//软引用非常适合缓存使用
