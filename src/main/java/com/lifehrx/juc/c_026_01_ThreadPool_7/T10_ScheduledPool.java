package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  执行定时任务的：
 *
 *  源码： 阿里不建议用
 *  定时器框架：
 *  Integer.MAX_VALUE ： 也是最大值
 *   public ScheduledThreadPoolExecutor(int corePoolSize) {
 *         super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
 *               new DelayedWorkQueue());
 *     }
 */
public class T10_ScheduledPool {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

		service.scheduleAtFixedRate(()->{
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MILLISECONDS);
		
	}
}
