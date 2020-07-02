/**
 * ThreadLocal线程局部变量
 * 
 * @author 马士兵
 */
package com.lifehrx.juc.c_022_RefTypeAndThreadLocal_5;

import java.util.concurrent.TimeUnit;

public class T00_ThreadLocal1 {

	volatile static Person p = new Person();
	
	public static void main(String[] args) {

		/**
		 * 一个线程里的内容不能被另一线程所影响（修改）
		 *
		 * 每个线程里的东西都独有一份
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
