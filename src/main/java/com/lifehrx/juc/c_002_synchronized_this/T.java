/**
 * synchronized�ؼ���
 * ��ĳ���������
 *
 * synchronized(this) ������ǰ����this
 *
 */

package com.lifehrx.juc.c_002_synchronized_this;

public class T {
	
	private int count = 10;
	
	public void m() {

		synchronized(this) { //�κ��߳�Ҫִ������Ĵ��룬�������õ�this����
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	
}

