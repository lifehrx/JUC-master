/**
 * 写时复制容器 copy on write
 *
 * 多线程环境下，写时效率低，读时效率高
 *
 * 适合写少读多的环境
 * 
 */
package com.lifehrx.juc.c_025_6;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {

	public static void main(String[] args) {

		List<String> lists = 
				//new ArrayList<>(); //这个会出并发问题！
				//new Vector();
				new CopyOnWriteArrayList<>(); // CopyOnWriteArrayList 读不加锁(原因:读的时候新[复制后的]老内容完全一样) 写时在原来的list copy 一份新的加载copyList的最后
		Random r = new Random();
		Thread[] ths = new Thread[100];
		
		for(int i=0; i<ths.length; i++) {
			Runnable task = new Runnable() {
	
				@Override
				public void run() {
					for(int i=0; i<1000; i++) lists.add("a" + r.nextInt(10000));
				}
				
			};
			ths[i] = new Thread(task);
		}
		
		
		runAndComputeTime(ths);
		
		System.out.println(lists.size());
	}
	
	static void runAndComputeTime(Thread[] ths) {
		long s1 = System.currentTimeMillis();
		Arrays.asList(ths).forEach(t->t.start());
		Arrays.asList(ths).forEach(t->{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		long s2 = System.currentTimeMillis();
		System.out.println(s2 - s1);
		
	}
}

