package com.lifehrx.juc.c_025_6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue : �������� (����ʵ�ֵ�BlockingQueue) ������������ģ��
 *
 * ʵ�����Զ�������ǰ�߳� �� �� park() ʵ�ֵ����� ��await �ĵײ�Ҳ����park()ʵ�֣�
 *
 * �޽���У�����һֱ��ӵ��ڴ����
 *
 * ��Queue �������ķ����� ���put() ȡtake()  ����Ӳ���ȥ�˻�ȡ�������ˣ��̻߳����� await״̬
 *
 *
 */
public class T05_LinkedBlockingQueue {

	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

	static Random r = new Random();

	public static void main(String[] args) {

		// 1. װ100�� a , ÿװһ����˯1000ms
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {

					// 1. ��������put()
					strs.put("a" + i); //������ˣ��ͻ�ȴ�
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "p1").start();

		// 2. 5���߳�һֱ�ڶ�������ȡ, ������п��˾͵���, �����Ԫ���˾�����ȡ����
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (;;) {
					try {
						// 2. ��������take()
						System.out.println("�߳����� = " + Thread.currentThread().getName() + "  take() ->" + strs.take()); //������ˣ��ͻ�ȴ�
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "c" + i).start();

		}
	}
}
