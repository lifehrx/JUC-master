/**
 * ����һ�������������
 *
 */

package com.lifehrx.juc.c_005_synchronize_run;

public class T implements Runnable {

	private volatile int count = 100;

	// �����������ȷ
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		T t = new T();
		for(int i=0; i<100; i++) {

			/**
			 * Thread(Runnable target, String name)
			 * ����һ���µ� Thread����
			 */
			new Thread(t, "thread = " + i).start();
		}
	}


	
}
