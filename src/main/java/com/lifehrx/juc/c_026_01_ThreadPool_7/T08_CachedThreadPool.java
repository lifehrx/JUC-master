package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ���￪���ֲ᲻������
 *
 * ��Ϊ��new ���ܶ��߳�
 */
public class T08_CachedThreadPool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println("��ExecutorService ����:�� " + service);
		for (int i = 0; i < 2; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println("��ExecutorService ����:��" + service);
		
		TimeUnit.SECONDS.sleep(80);
		
		System.out.println("��ExecutorService ����:��" + service);
	}
}
