package com.lifehrx.juc.c_026_00_interview_7.A1B2C3;

import java.util.concurrent.Exchanger;

/**
 * ����������� �� �� ��
 * Exchanger �� ���������߳�֮�以�ཻ�����ݵ�
 */
public class T12_00_Exchanger_Not_Work_9 {
    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{
            for(int i=0; i<aI.length; i++) {
                // ���������� 2��sout��һ��˭�ȴ�ӡ
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
                // ����������
                System.out.print(aC[i]);
            }
        }).start();
    }
}
