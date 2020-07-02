/**
 *
 * һ��ͬ���������Ե�������һ��ͬ��������һ���߳��Ѿ�ӵ��ĳ������������ٴ������ʱ����Ȼ��õ��ö������.
 * Ҳ����˵synchronized��õ����ǿ������
 * �����Ǽ̳����п��ܷ��������Σ�������ø����ͬ������
 *
 */
package com.lifehrx.juc.c_010_extend;

import java.util.concurrent.TimeUnit;

public class T {

	// ��������������m() ��Ϊ����Ҳ�ǵ��ø����m()����
	synchronized void m() {
		System.out.println("father m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("father m end");
	}
	
	public static void main(String[] args) {
		new TT().m();
	}
	
}

class TT extends T {

	@Override
	synchronized void m() {
		System.out.println("child m start");
		// ======= ���� ���� ===========
		super.m();
		System.out.println("child m end");
	}
}
