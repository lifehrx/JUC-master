/**
 * ��N�Ż�Ʊ��ÿ��Ʊ����һ�����
 * ͬʱ��10�����ڶ�����Ʊ
 * ��дһ��ģ�����
 * 
 * ��������ĳ�����ܻ������Щ���⣿
 *  
 * ʹ��Vector����Collections.synchronizedXXX
 * ����һ�£������ܽ��������

 */
package com.lifehrx.juc.c_024_FromVectorToQueue_6;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {

	// 1. Vector �̰߳�ȫ
	static Vector<String> tickets = new Vector<>();
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("Ʊ ��ţ�" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{

				// ���� �� a. tickets.size() ������
				while(tickets.size() > 0) {

					// 1000 ��Ʊ, ˯10ms
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// ���⣺b. tickets.remove() Ҳ������ �����м�ġ�˯�ߡ�����û�м���, ˯���ڼ�ͬ�����кܶ��߳�ȥ����, ���Ҳ��������
					System.out.println("������--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
