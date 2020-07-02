/**
 * 比较并行速度和串行速度： 线程池运行的速度快的多
 *
 * 可以让任务真正的并行处理
 *
 *
 * 注意：4个线程不一定运行在4个核上,
 */
package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class T09_FixedThreadPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		// 计算1~20万的数中的质数 ： 一个线程运行
		getPrime(1, 200000); 
		long end = System.currentTimeMillis();
		System.out.println("一个线程处理运行时间：" + (end - start));

		// 4核CPU : 4个CPU
		final int cpuCoreNum = 4;

		// 用一个固定的线程池：有一个好处可以进行并行计算 : 例如4
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

		// 分别计算 : 切分4部分
		MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);

		// 分别扔到线程池里
		Future<List<Integer>> f1 = service.submit(t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);
		
		start = System.currentTimeMillis();
		 // 从线程池里取数
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println("4个线程的线程池处理运行时间 ：" + (end - start));
	}
	
	static class MyTask implements Callable<List<Integer>> {
		int startPos, endPos;
		
		MyTask(int s, int e) {
			this.startPos = s;
			this.endPos = e;
		}
		
		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos, endPos);
			return r;
		}
		
	}

	// 判断一个数是不是质数
	static boolean isPrime(int num) {
		for(int i=2; i<=num/2; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

	// 根据起始位置终止位置：取出中间全部的一堆质数 为了切分任务
	static List<Integer> getPrime(int start, int end) {
		List<Integer> results = new ArrayList<>();
		for(int i=start; i<=end; i++) {
			if(isPrime(i)) results.add(i);
		}
		
		return results;
	}
}
