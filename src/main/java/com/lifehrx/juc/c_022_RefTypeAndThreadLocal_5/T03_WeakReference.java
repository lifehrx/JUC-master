/**
 * 弱引用遭到gc就会回收
 *
 */
package com.lifehrx.juc.c_022_RefTypeAndThreadLocal_5;

import java.lang.ref.WeakReference;

public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<T05_M> m = new WeakReference<>(new T05_M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());


        ThreadLocal<T05_M> tl = new ThreadLocal<>();
        tl.set(new T05_M());
        tl.remove();

    }
}

