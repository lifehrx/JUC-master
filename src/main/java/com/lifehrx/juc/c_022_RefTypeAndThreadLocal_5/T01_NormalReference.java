package com.lifehrx.juc.c_022_RefTypeAndThreadLocal_5;

import java.io.IOException;

/**
 * 强引用
 */
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {

        T05_M m = new T05_M();

        // m 没有引用了 接下来就是会被回收
        m = null;
        System.gc(); //DisableExplicitGC

        // 阻塞当前线程
        System.in.read();
    }
}
