/**
 *
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 *
 */
package com.lifehrx.juc.c_010_extend;

import java.util.concurrent.TimeUnit;

public class T {

	// 锁的是这里：父类的m() 因为子类也是调用父类的m()方法
	synchronized void m() {
		System.out.println("father m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("father m end");
	}
	
	public static void main(String[] args) {
		new TT().m();
	}
	
}

class TT extends T {

	@Override
	synchronized void m() {
		System.out.println("child m start");
		// ======= 调用 父类 ===========
		super.m();
		System.out.println("child m end");
	}
}
