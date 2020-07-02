package com.lifehrx.juc.c_012_Volatile;

/**
 * 测试：汇编语言
 * new 一个对象的指令 4条
 *
 * 所以这些指令可能会被打断，或是重新排序
 */
public class JustTest {
    public static void main(String[] args) {
        Object o = new Object();
    }
}
