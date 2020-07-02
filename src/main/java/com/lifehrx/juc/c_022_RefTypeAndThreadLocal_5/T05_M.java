package com.lifehrx.juc.c_022_RefTypeAndThreadLocal_5;

public class T05_M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
