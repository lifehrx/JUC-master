package com.lifehrx.juc.c_025_6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 特殊的 BQ
 *
 * DelayQueue : 按照紧迫程度来排序
 *
 * 应用场景 ：10:00 做任务一 11:00 做任务二
 *
 */
public class T07_01_DelayQueue {

	static BlockingQueue<MyTask> tasks = new DelayQueue<>();

	static Random r = new Random();
	
	static class MyTask implements Delayed {
		String name;
		long runningTime;
		
		MyTask(String name, long rt) {
			this.name = name;
			this.runningTime = rt;
		}

		@Override
		public int compareTo(Delayed o) {
			if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
				return -1;
			else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) 
				return 1;
			else 
				return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}
		
		
		@Override
		public String toString() {
			return name + " " + runningTime;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		long now = System.currentTimeMillis();
		MyTask t1 = new MyTask("t1当前时间 = ", now + 1000);
		MyTask t2 = new MyTask("t2当前时间 = ", now + 2000);
		MyTask t3 = new MyTask("t3当前时间 = ", now + 1500);
		MyTask t4 = new MyTask("t4当前时间 = ", now + 2500);
		MyTask t5 = new MyTask("t5当前时间 = ", now + 500);
		
		tasks.put(t1);
		tasks.put(t2);
		tasks.put(t3);
		tasks.put(t4);
		tasks.put(t5);
		
		System.out.println(tasks);
		System.out.println();
		
		for(int i=0; i<5; i++) {
			System.out.println(tasks.take());
		}
	}
}
