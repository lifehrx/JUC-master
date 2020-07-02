package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * compute() : ÿ������ִ�е�ʱ��ͻᱻ����
 */
public class T12_ForkJoinPool {
	// 100�����
	static int[] nums = new int[1000000];

	// ��С������Ƭ������ 5�����
	static final int MAX_NUM = 50000;

	static Random r = new Random();
	
	static {
		for(int i=0; i<nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		System.out.println("---" + Arrays.stream(nums).sum()); //stream api
	}

	/**
	 * Recursive : �ݹ�ģ��Ѵ�����ֽ��С����С��������ֽ⣩
	 * RecursiveAction �� ����
	 * û�з���ֵ��
	 */
	static class AddTask extends RecursiveAction {

		int start, end;

		AddTask(int s, int e) {
			start = s;
			end = e;
		}

		/**
		 * ���� null
		 */
		@Override
		protected void compute() {

			// �жϸ��ҵ����Ƿ񳬳�5��
			if(end-start <= MAX_NUM) {
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				System.out.println("from:" + start + " to:" + end + " = " + sum);
			} else {

				// �����ҳ��м��������fork���ֳ�2��������
				int middle = start + (end-start)/2;

				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();
			}


		}

	}

	/**
	 * ������ �̳� RecursiveTask<Long>
	 *
	 * �����з���ֵ��
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
		// ��һ : û�з���
		/*ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		fjp.execute(task);*/

		T12_ForkJoinPool temp = new T12_ForkJoinPool();

		// ������ �з���ֵ��
		ForkJoinPool fjp = new ForkJoinPool();
		AddTaskRet task = new AddTaskRet(0, nums.length);
		fjp.execute(task);
		long result = task.join();
		System.out.println("result : = " + result);

		// ����һ��
		//System.in.read();
		
	}
}
