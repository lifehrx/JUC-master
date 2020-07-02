/*

package com.lifehrx.juc.c_021_03_VarHandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class T01_HelloVarHandle {

    int x = 8;

    // 通过 handle 直接操作值：如 8 - 9
    private static VarHandle handle;

    static {
        try {
            // 找一个handle : 在T01_HelloVarHandle 类里 名字为x 类型是int
            // x 和 handle 都指向同一块内存
            handle = MethodHandles.lookup().findVarHandle(T01_HelloVarHandle.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T01_HelloVarHandle t = new T01_HelloVarHandle();

        */
/**
         * 应用场景 ：普通的属性变成-原子性（线程安全）操作
         * VarHandle : 理解为直接操作二进制码
         * 比反射快
         * JDK 9+
         *//*

        //plain read / write
        System.out.println((int)handle.get(t));
        handle.set(t,9);
        System.out.println(t.x);

        handle.compareAndSet(t, 9, 10);
        System.out.println(t.x);

        handle.getAndAdd(t, 10);
        System.out.println(t.x);

    }
}

*/
