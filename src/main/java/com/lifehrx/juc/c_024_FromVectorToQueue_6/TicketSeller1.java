/**
 * ��N�Ż�Ʊ��ÿ��Ʊ����һ�����
 * ͬʱ��10�����ڶ�����Ʊ
 * ��дһ��ģ�����
 * 
 * ��������ĳ�����ܻ������Щ���⣿
 * �ظ����ۣ��������ۣ�
 *
 */
package com.lifehrx.juc.c_024_FromVectorToQueue_6;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {

	// 1. new һ������ �����⣺���̷߳���һ����������û������ ÿ���̶߳�������Ʊ��>0 ��ͬʱ��������
	static List<String> tickets = new ArrayList<>();

	// 2. ����װ 10����
	static {
		for(int i=0; i<10000; i++) tickets.add("Ʊ��ţ�" + i);
	}

	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			new Thread(()->{
				// 3. ��Ʊ��һֱȡ remove
				while(tickets.size() > 0) {
					System.out.println("������--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
