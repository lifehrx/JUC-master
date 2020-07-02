package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T07_SingleThreadPool_8 {
	public static void main(String[] args) {
		/**
		 * 所有的线程池都是在 ExecutorService 上继承的
		 *
		 * newSingleThreadExecutor() 单线程的线程池
		 * 一个线程的线程池： 保证任务的顺序执行
		 *
		 * 阿里开发手册：建议不让用JDK自带的线程池，因为有限制
		 */
		ExecutorService service = Executors.newSingleThreadExecutor();
		for(int i=0; i<5; i++) {
			final int j = i;
			service.execute(()->{
				
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
			
	}
}
