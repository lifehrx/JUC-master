package com.lifehrx.juc.c_023_02_FromHashtableToCHM_6;

import java.util.Hashtable;
import java.util.UUID;

/**
 * ���ش�����ģ����������
 *
 * 100������� ��ÿ���̵߳�����ȡһ��� Ȼ����뵽Hashtable����
 */
public class T01_TestHashtable {

    static Hashtable<UUID, UUID> m = new Hashtable<>();

    //=========================��֮ǰ���߳������ɺ���==============================================
    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }
    //==================ԭ���ǣ�д����������һ���ģ�����ÿһ�ζ���������ʵ�����ǲ�һ���ģ�==============

    // 40:00
    static class MyThread extends Thread {

        //
        int start;
        // 100 ���߳�װ1����� �� gap = 1���̸߳���װ������
        int gap = count/THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            // ��0��ʼҲ�Ǵ�1��� ��1��ʼҲ�Ǵ�1���
            for(int i=start; i<start+gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        // �߳����飺������100���߳�
        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i=0; i<threads.length; i++) {
            // ÿ���߳���ʼֵ��ͬ:0-1�� 1��01 - 2�� (ÿ���̼߳�¼������������Щ����)
            threads[i] =
            new MyThread(i * (count/THREAD_COUNT));
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Hashtable ��������ʱ�� = " + (end - start) + " ����");

        System.out.println("Hashtable ������̸߳���" + m.size() + " (��֤��100���)");

        //--------------- �� ��50:20��Ƶʱ�䣩 --------------------

        start = System.currentTimeMillis();
        // ����100���̣߳���1000��εĵ�10��Ԫ��
        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println("Hashtable�� " + (end - start) + " ����");
    }
}
