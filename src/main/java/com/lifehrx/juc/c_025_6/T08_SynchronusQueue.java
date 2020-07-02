package com.lifehrx.juc.c_025_6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 133 �� 00
 * SynchronusQueue �� ͬ��Queue
 * ����Ϊ0 ����װ�����ġ�
 * ֪ͨ������
 * �����̣߳� t1Ҫ��t2�߳� �ݶ����� �൱���ְ��ֵĵݹ�ȥ���м䲻���ڶ�����ʱ����������
 * ���߳�û�о�һֱ���ţ�ʲôʱ���߳����ˣ���ֱ�ӷŵ��Է����
 * �൱�ڣ������߳̽�������  ����exchange �򵥣�
 */
public class T08_SynchronusQueue { //����Ϊ0
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		//strs.put("aaa"); //�����ȴ�����������
		//strs.put("bbb");
		//strs.add("aaa"); // ���� �� Exception in thread "main" java.lang.IllegalStateException: Queue full
		System.out.println("strs.size() = " + strs.size());
	}
}
