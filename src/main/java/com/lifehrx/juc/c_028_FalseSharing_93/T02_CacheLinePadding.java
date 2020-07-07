package com.lifehrx.juc.c_028_FalseSharing_93;

/**
 * д���� �� ����ͬһ������
 * ģ�⣺�����߳�ͬʱ�ڸ��¸���������x��ֵ��
 *       ����ͬһ�������С�
 *       Ч�ʱ�T01�ߣ�ʱ�����ˡ�
 */
public class T02_CacheLinePadding {
    // 7*8 = 56 �ֽ� ��7��������Ϊ�˻����ж��룬��䣬Ч��������
    private static class Padding {
        // �ռ���Ȼ�˷���һ�㣬����Ч�������ˡ�
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    // �̳�Padding �� T �����Լ�ռһ�У����Բ��������ռһ��
    private static class T extends Padding {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

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
        System.out.println("����ͬһ����������ʱ�䣺" + (System.nanoTime() - start)/100_0000);
    }
}
