package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * compute() : 每次任务被执行的时候就会被调用
 */
public class T12_ForkJoinPool {
	// 100万个数
	static int[] nums = new int[1000000];

	// 最小的任务片不超过 5万个数
	static final int MAX_NUM = 50000;

	static Random r = new Random();
	
	static {
		for(int i=0; i<nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		System.out.println("---" + Arrays.stream(nums).sum()); //stream api
	}

	/**
	 * Recursive : 递归的（把大任务分解成小任务，小任务继续分解）
	 * RecursiveAction ： 任务
	 * 没有返回值的
	 */
	static class AddTask extends RecursiveAction {

		int start, end;

		AddTask(int s, int e) {
			start = s;
			end = e;
		}

		/**
		 * 返回 null
		 */
		@Override
		protected void compute() {

			// 判断给我的数是否超出5万
			if(end-start <= MAX_NUM) {
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				System.out.println("from:" + start + " to:" + end + " = " + sum);
			} else {

				// 否则，找出中间的数，再fork，分成2个子任务
				int middle = start + (end-start)/2;

				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();
			}


		}

	}

	/**
	 * 法二： 继承 RecursiveTask<Long>
	 *
	 * 可以有返回值的
	 */
	static class AddTaskRet extends RecursiveTask<Long> {
		
		private static final long serialVersionUID = 1L;
		int start, end;
		
		AddTaskRet(int s, int e) {
			start = s;
			end = e;
		}

		/**
		 * Long
		 * @return
		 */
		@Override
		protected Long compute() {
			
			if(end-start <= MAX_NUM) {
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				return sum;
			} 
			
			int middle = start + (end-start)/2;
			
			AddTaskRet subTask1 = new AddTaskRet(start, middle);
			AddTaskRet subTask2 = new AddTaskRet(middle, end);
			subTask1.fork();
			subTask2.fork();
			
			return subTask1.join() + subTask2.join();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// 法一 : 没有返回
		/*ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		fjp.execute(task);*/

		T12_ForkJoinPool temp = new T12_ForkJoinPool();

		// 法二： 有返回值的
		ForkJoinPool fjp = new ForkJoinPool();
		AddTaskRet task = new AddTaskRet(0, nums.length);
		fjp.execute(task);
		long result = task.join();
		System.out.println("result : = " + result);

		// 阻塞一下
		//System.in.read();
		
	}
}
