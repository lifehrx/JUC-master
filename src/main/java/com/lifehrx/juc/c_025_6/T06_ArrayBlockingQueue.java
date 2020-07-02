package com.lifehrx.juc.c_025_6;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 115:00 - 118:00
 *
 * �н��޵� Queue
 */
public class T06_ArrayBlockingQueue {

	// Queue �Ĵ�СΪ10
	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {

		// ������������10��Ԫ��
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
			System.out.println("a" + i);
		}

		// ���ⲽ��û�а취��ӽ�ȥ�˵�11����, ��Ϊ����
		//strs.put("aaa"); //���˾ͻ�ȴ�(�ȴ�������������)����������
		//strs.add("aaa"); // �����쳣

		//strs.offer("aaa"); // �����쳣
		strs.offer("aaa", 1, TimeUnit.SECONDS); // ��1s��, ���ܲ��ܼӽ�ȥ
		
		System.out.println(strs);
	}
}
