/**
 * synchronized 关键字
 * 对某个对象加锁
 *
 * 锁定是 o 对象 ； 用o对象的2位来标识这个锁
 *
 * 例如：一个对象是64位现在用2位来标识锁
 *
 */

package com.lifehrx.juc.c_001_synchronized;

public class T {
	
	private int count = 10;
	private Object o = new Object();
	
	public void m() {
		// 其实这里的锁锁定的是o(并不是锁定对象),拿到了这把锁才能执行这段代码
		synchronized(o) {
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	
}

