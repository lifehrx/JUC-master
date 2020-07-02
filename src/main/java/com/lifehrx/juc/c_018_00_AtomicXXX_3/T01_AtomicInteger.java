package com.lifehrx.juc.c_018_00_AtomicXXX_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 */
public class T01_AtomicInteger {

	/**
	 * 1. 使用 volatile 方法
	 */
	volatile int count1 = 0;
	/*synchronized*/ void m1() {
		for (int i = 0; i < 10000; i++)
			count1++;
	}

	/**
	 * 2. 使用 AtomicInteger 方法
	 */
	AtomicInteger count = new AtomicInteger(0);

	void m2() {
		for (int i = 0; i < 10000; i++)
			count.incrementAndGet();
	}

	/*synchronized*/ void m() { 
		for (int i = 0; i < 10000; i++)
			//if count1.get() < 1000
			count.incrementAndGet(); //count1++
	}

	public static void main(String[] args) {
		T01_AtomicInteger t = new T01_AtomicInteger();

		List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {

			//threads.add(new Thread(t::m1, "thread-" + i));
			threads.add(new Thread(t::m2, "thread-" + i));
		}

		threads.forEach((o) -> o.start());

		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		//System.out.println(t.count1);
		System.out.println(t.count);

	}

}
