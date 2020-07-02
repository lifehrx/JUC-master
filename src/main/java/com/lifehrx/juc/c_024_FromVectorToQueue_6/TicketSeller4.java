/**
 * ��N�Ż�Ʊ��ÿ��Ʊ����һ�����
 * ͬʱ��10�����ڶ�����Ʊ
 * ��дһ��ģ�����
 * 
 * ��������ĳ�����ܻ������Щ���⣿
 * �ظ����ۣ��������ۣ�
 * 
 * ʹ��Vector����Collections.synchronizedXXX
 * ����һ�£������ܽ��������
 * 
 * �������A��B����ͬ���ģ���A��B��ɵĸ��ϲ���Ҳδ����ͬ���ģ���Ȼ��Ҫ�Լ�����ͬ��
 * ������������ж�size�ͽ���remove������һ������ԭ�Ӳ���
 * 
 * ʹ��ConcurrentQueue��߲�����
 *
 */
package com.lifehrx.juc.c_024_FromVectorToQueue_6;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {

	// 1. Queue ���� �� �Ժ���̶߳࿼����Queue��Ԫ�ز��ظ��� , �ٿ��� List
	// ConcurrentLinkedQueue Դ������CASʵ�ֵ�Ч�ʽϸ�
	// sync �� CSA Ҫ������������, �Ƚ�Ч��
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	
	static {
		// 2. 1000 ��Ʊ
		for(int i=0; i<1000; i++) tickets.add("Ʊ ��ţ�" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{

				// 3. �ۻ�Աһֱ����Ʊ
				while(true) {
					// poll �� s ������������û�����⣬ ��������ͻ������⣬��Ϊ�ǹ�������Դ
					String s = tickets.poll();
					// 4. ��ĳһʱ��һ����ȡ,����ûƱ��, ������
					if(s == null) break;
					else System.out.println("������--" + s);
				}
			}).start();
		}
	}
}
