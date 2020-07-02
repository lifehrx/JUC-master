/**
 * ThreadLocal�ֲ߳̾�����
 * 
 * @author ��ʿ��
 */
package com.lifehrx.juc.c_022_RefTypeAndThreadLocal_5;

import java.util.concurrent.TimeUnit;

public class T00_ThreadLocal1 {

	volatile static Person p = new Person();
	
	public static void main(String[] args) {

		/**
		 * һ���߳�������ݲ��ܱ���һ�߳���Ӱ�죨�޸ģ�
		 *
		 * ÿ���߳���Ķ���������һ��
		 */
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(p.name);
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.name = "lisi";
		}).start();
	}
}

class Person {
	String name = "zhangsan";
}
