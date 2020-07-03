/**
 * ReentrantLock������ָ��Ϊ��ƽ�� ��
 *
 * ���ȴ�ʱ�䳤�Ŀ����ȵõ������
 */
package com.lifehrx.juc.c_020_01_3;

import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock5 extends Thread {
		
	private static ReentrantLock lock=new ReentrantLock(true); //����Ϊtrue��ʾΪ��ƽ������Ա�������
    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"�����");
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        T05_ReentrantLock5 rl=new T05_ReentrantLock5();
        Thread th1=new Thread(rl);
        Thread th2=new Thread(rl);
        th1.start();
        th2.start();
    }
}
