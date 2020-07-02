package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * WorkStealingPool : 在一个线程池里每个线程维护自己的任务队列。
 * 当1个线程执行完了自己的任务，去其他没有执行完的线程队列里偷任务，然后自己执行。
 * 优点：灵活，可以分担任务
 *
 * 被人偷任务的时候队列依旧要加锁，或是自旋
 *
 *
 * 源码：本质上是一个 ForkJoinPool ：
 * 适合把大任务切分成（Fork）小任务。但最后执行的结果要汇总(Join)。
 */
public class T11_WorkStealingPool {
	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());

		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000)); //daemon
		service.execute(new R(2000));
		
		//由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
		System.in.read(); 
	}

	static class R implements Runnable {

		int time;

		R(int t) {
			this.time = t;
		}

		@Override
		public void run() {
			
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(time  + " " + Thread.currentThread().getName());
			
		}

	}
}
