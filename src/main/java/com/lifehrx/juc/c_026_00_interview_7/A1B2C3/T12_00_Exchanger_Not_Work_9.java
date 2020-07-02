package com.lifehrx.juc.c_026_00_interview_7.A1B2C3;

import java.util.concurrent.Exchanger;

/**
 * 这个方法不行 × × ×
 * Exchanger ： 用于两个线程之间互相交换数据的
 */
public class T12_00_Exchanger_Not_Work_9 {
    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{
            for(int i=0; i<aI.length; i++) {
                // 问题出在这儿 2个sout不一定谁先打印
                System.out.print(aI[i]);
                try {
                    exchanger.exchange("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for(int i=0; i<aC.length; i++) {
                try {
                    exchanger.exchange("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 问题出在这儿
                System.out.print(aC[i]);
            }
        }).start();
    }
}
