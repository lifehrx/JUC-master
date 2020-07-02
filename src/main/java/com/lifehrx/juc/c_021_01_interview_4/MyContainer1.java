/**
 * �����⣺дһ���̶�����ͬ��������ӵ��put��get�������Լ�getCount������
 * �ܹ�֧��2���������߳��Լ�10���������̵߳���������
 * 
 * ʹ��wait��notify/notifyAll��ʵ��
 *
 */
package com.lifehrx.juc.c_021_01_interview_4;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {

	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10; //���10��Ԫ��
	private int count = 0;

	// ��synchronized :��Ϊ ++count;
	public synchronized void put(T t) {
		while(lists.size() == MAX) { //����Ϊʲô��while��������if�� 114��00:00
			try {
				this.wait(); //effective java
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lists.add(t);
		++count;
		this.notifyAll(); //֪ͨ�������߳̽�������
	}

	// synchronized : count --
	public synchronized T get() {
		T t = null;
		// == 0 ȡ����
		while(lists.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		count --;
		this.notifyAll(); //֪ͨ�����߽�������
		return t;
	}
	
	public static void main(String[] args) {
		MyContainer1<String> c = new MyContainer1<>();
		//�����������߳�
		for(int i=0; i<10; i++) {
			new Thread(()->{
				for(int j=0; j<5; j++) System.out.println(c.get());
			}, "c �������߳� = " + i).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//�����������߳�
		for(int i=0; i<2; i++) {
			new Thread(()->{
				for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
			}, "p �������߳� =" + i).start();
		}
	}
}
