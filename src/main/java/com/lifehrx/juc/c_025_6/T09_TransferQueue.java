package com.lifehrx.juc.c_025_6;

import java.util.concurrent.LinkedTransferQueue;

/**
 * TransferQueue : ����
 *
 * ʹ�ó��� �� Ҫ�������
 * 1.����������Ǯ�˲�����(������������MQʵ�֣������Լ�д)
 * 2.�����ӡ
 *
 */
public class T09_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
		
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		// �ص� �� put()װ������� ���� transfer()װ���������˰���ȡ��
		strs.transfer("aaa");
		
		/*strs.put("aaa");
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
	}
}
