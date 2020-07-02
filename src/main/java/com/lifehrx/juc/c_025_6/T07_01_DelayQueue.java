package com.lifehrx.juc.c_025_6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * ����� BQ
 *
 * DelayQueue : ���ս��ȳ̶�������
 *
 * Ӧ�ó��� ��10:00 ������һ 11:00 �������
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
		MyTask t1 = new MyTask("t1��ǰʱ�� = ", now + 1000);
		MyTask t2 = new MyTask("t2��ǰʱ�� = ", now + 2000);
		MyTask t3 = new MyTask("t3��ǰʱ�� = ", now + 1500);
		MyTask t4 = new MyTask("t4��ǰʱ�� = ", now + 2500);
		MyTask t5 = new MyTask("t5��ǰʱ�� = ", now + 500);
		
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
