/**
 * synchronized关键字
 *
 * 对某个对象加锁
 */

package com.lifehrx.juc.c_004_synchronized_class;

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //这里等同于synchronized(this)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {
		/**
		 * 锁的是T的class的对象
		 */
		synchronized(T.class) { //考虑一下这里写synchronized(this)是否可以？
			count --;
		}
	}

}
