/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行？
 *
 * 容易产生脏读问题（dirtyRead）
 */

package com.lifehrx.juc.c_008;

import java.util.concurrent.TimeUnit;

public class Account {
	// 账户名称
	String name;
	// 余额
	double balance;
	
	public synchronized void set(String name, double balance) {
		this.name = name;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		this.balance = balance;
	}

	// 加了 synchronized 不会产生脏读
	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}
	
	
	public static void main(String[] args) {
		Account a = new Account();

		//zhangsan 100元 睡1s （没有加锁所以在睡的1s中,读到了中间数据, 即脏数据）
		new Thread(()->a.set("zhangsan", 100.0)).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 脏读
		System.out.println(a.getBalance("zhangsan"));

		/**================================================*/

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(a.getBalance("zhangsan"));
	}
}
