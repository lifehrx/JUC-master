package com.lifehrx.juc.c_028_FalseSharing_93;

/**
 * д��һ �� ģ����ͬһ������
 * ģ�⣺�����߳�ͬʱ�ڸ��¸���������x��ֵ��
 *      ����x��ͬһ���������ڱ��ı�ֵ��
 *
 */
public class T01_CacheLinePadding {

    // Long ���� �� 8 �ֽ�
    private static class T {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    // �ڴ�����new�������� Long ���͵�����, ����1,2λ��ͬһ��������
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {

        // �߳�1��ѭ��100��Σ�һֱ�ڸı�x��ֵ
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        // �߳�2��ѭ��100��Σ�ͬ����һֱ�ڸı�x��ֵ
        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("��ͬһ����������ʱ�� �� " + (System.nanoTime() - start)/100_0000);
    }
}
