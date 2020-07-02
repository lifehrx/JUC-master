/**
 * 分析一下这个程序的输出
 *
 */

package com.lifehrx.juc.c_005_synchronize_run;

public class T implements Runnable {

	private volatile int count = 100;

	// 这种情况是正确
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		T t = new T();
		for(int i=0; i<100; i++) {

			/**
			 * Thread(Runnable target, String name)
			 * 分配一个新的 Thread对象。
			 */
			new Thread(t, "thread = " + i).start();
		}
	}


	
}
