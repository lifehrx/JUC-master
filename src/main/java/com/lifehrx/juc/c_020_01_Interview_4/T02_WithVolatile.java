/**
 * �����������⣺���Ա�����
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
 * ��lists���volatile֮��t2�ܹ��ӵ�֪ͨ�����ǣ�t2�̵߳���ѭ�����˷�cpu�����������ѭ����
 * ���ң������if �� break֮�䱻����̴߳�ϣ��õ��Ľ��Ҳ����ȷ��
 * ����ô���أ�
 */
package com.lifehrx.juc.c_020_01_Interview_4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class T02_WithVolatile {

	//���volatile:ʹt2�ܹ��õ�֪ͨ(volatile ���ε������ã������������ָ��Ķ����Ա�����仯��
	// �߳̿�������ֻ�������ñ���ı�ſ��ĵ�)�����Բ���д��
	//volatile List lists = new LinkedList();

	// �Ƚ����Ƶ�д�� : ͬ������
	volatile List lists = Collections.synchronizedList(new LinkedList<>());

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {

		T02_WithVolatile c = new T02_WithVolatile();

		new Thread(() -> {
			for(int i=0; i<10; i++) {
				c.add(new Object());
				System.out.println("add = " + i);

				// ��˯1�Ͳ��У����뻹�������⣩, ˯��1�Ϳ��ԣ�˯��1s���ڼ��߳̾�д��ȥ��
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();
		
		new Thread(() -> {
			while(true) {
				if(c.size() == 5) {
					break;
				}
			}
			System.out.println("t2 ����");
		}, "t2").start();
	}
}
