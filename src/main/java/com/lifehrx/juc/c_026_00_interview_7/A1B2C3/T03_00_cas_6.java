package com.lifehrx.juc.c_026_00_interview_7.A1B2C3;


public class T03_00_cas_6 {


    // 信号灯 T1 T2
    // 为什么要用enum不用 1,2 ?枚举类更严谨只能取T1 T2两个, 如果是Integer 可能1,2,3
    enum ReadyToRun {T1, T2}

    static volatile ReadyToRun r = ReadyToRun.T1; //思考为什么必须volatile: 保证线程间的可见性

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {

            for (char c : aI) {
                // 自旋的写法都是占用CPU的
                while (r != ReadyToRun.T1) {}
                System.out.print(c);
                r = ReadyToRun.T2;
            }

        }, "t1").start();

        new Thread(() -> {

            for (char c : aC) {

                while (r != ReadyToRun.T2) {}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();
    }
}


