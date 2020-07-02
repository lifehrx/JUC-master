package com.lifehrx.juc.c_000_thread;

/**
 * ��Ƶ
 * Thread:����״̬���еĿ����ô���ģ������еĲ����ԣ�
 * getState()
 *
 * ��PPT��ͼ ��1. ���߳���߲���(�е��Ʊʼ�)
 *
 * �������̹߳ر�stop()�������� : ���߳��������н�����ǿ��ֹͣ�����״̬��һ�¡�
 * interpret() ��Ҫ���������ƹ��̴��� �� ����һ�����߳�sleep() �ܾ�
 *
 */
public class T04_ThreadState {

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("�߳�״̬��" + this.getState());

            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();

        System.out.println("�߳�״̬��" + t.getState());

        t.start();
        System.out.println("�߳�״̬��" + t.getState());

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("�߳�״̬��" + t.getState());

    }
}
