/**
 * synchronized �ؼ���
 * ��ĳ���������
 *
 * ������ o ���� �� ��o�����2λ����ʶ�����
 *
 * ���磺һ��������64λ������2λ����ʶ��
 *
 */

package com.lifehrx.juc.c_001_synchronized;

public class T {
	
	private int count = 10;
	private Object o = new Object();
	
	public void m() {
		// ��ʵ���������������o(��������������),�õ������������ִ����δ���
		synchronized(o) {
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	
}

