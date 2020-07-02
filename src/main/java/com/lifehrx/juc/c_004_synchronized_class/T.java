/**
 * synchronized�ؼ���
 *
 * ��ĳ���������
 */

package com.lifehrx.juc.c_004_synchronized_class;

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //�����ͬ��synchronized(this)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {
		/**
		 * ������T��class�Ķ���
		 */
		synchronized(T.class) { //����һ������дsynchronized(this)�Ƿ���ԣ�
			count --;
		}
	}

}
