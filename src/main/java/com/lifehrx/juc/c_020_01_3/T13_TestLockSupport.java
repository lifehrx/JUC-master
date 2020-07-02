package com.lifehrx.juc.c_020_01_3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport : ����֧��
 *
 * ����ĳһ���̣߳�������
 *
 * unpark()�������� park()֮ǰ ,����wait()��notify() ������
 *
 * �ײ����ά��һ��count
 */
public class T13_TestLockSupport {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i == 5) {
                    // ��ǰ�߳�ֹͣ
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        // t �߳̿�ʼ �����ѣ�
        LockSupport.unpark(t);

       /* try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 8 senconds!");

        LockSupport.unpark(t);*/

    }
}
