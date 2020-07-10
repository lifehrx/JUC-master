package com.lifehrx.juc.c_020_01_3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier : ѭ��դ�� �������˾Ͱ����Ƶ���
 *
 * 19 ���߳��ڵ�����Ϊ��20�����˾ͷ����� ��100/20 = 5����
 *
 * Ӧ�ó���1������ ��̫������һ�´����ݿ��������龰�������������ݿ⣬�����Ӿ���ÿ20���������ݣ�
 * ��ʵ����ʹ�� �� Guava RateLimiter
 *
 * Ӧ�ó���2�� ���Ʋ��������ļ������ݿ⣬ ���硣������ִ��3�ֲ����ȶ���������Ӧ�����ݣ��ٽ�����һ��������
 */
public class T07_TestCyclicBarrier {

    public static void main(String[] args) {
        // ��20�˷��� ������new������
        /**
         * д��һ ��
         */
        //CyclicBarrier barrier = new CyclicBarrier(20);

        /**
         * д���� �� 2������
         */
        //CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("����"));

        /**
         * д�������Ƚ����ŵ�д�� 2������
         */

        CyclicBarrier barrier = new CyclicBarrier(1, new Runnable() {
            int i = 0;

            @Override
            public void run() {
                System.out.println(++i + " ���ˣ�����");
            }
        });


        for (int i = 0; i < 100; i++) {

            new Thread(() -> {
                try {

                    // ��1....n ���߳������� barrierդ��������ţ��ȹ�20���˷�����ÿ���̱߳������await() ��Ҫ������ס��
                    barrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
