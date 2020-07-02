/**
 * �Ƚϲ����ٶȺʹ����ٶȣ� �̳߳����е��ٶȿ�Ķ�
 *
 * ���������������Ĳ��д���
 *
 *
 * ע�⣺4���̲߳�һ��������4������,
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
		// ����1~20������е����� �� һ���߳�����
		getPrime(1, 200000); 
		long end = System.currentTimeMillis();
		System.out.println("һ���̴߳�������ʱ�䣺" + (end - start));

		// 4��CPU : 4��CPU
		final int cpuCoreNum = 4;

		// ��һ���̶����̳߳أ���һ���ô����Խ��в��м��� : ����4
		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

		// �ֱ���� : �з�4����
		MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);

		// �ֱ��ӵ��̳߳���
		Future<List<Integer>> f1 = service.submit(t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);
		
		start = System.currentTimeMillis();
		 // ���̳߳���ȡ��
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		end = System.currentTimeMillis();
		System.out.println("4���̵߳��̳߳ش�������ʱ�� ��" + (end - start));
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

	// �ж�һ�����ǲ�������
	static boolean isPrime(int num) {
		for(int i=2; i<=num/2; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

	// ������ʼλ����ֹλ�ã�ȡ���м�ȫ����һ������ Ϊ���з�����
	static List<Integer> getPrime(int start, int end) {
		List<Integer> results = new ArrayList<>();
		for(int i=start; i<=end; i++) {
			if(isPrime(i)) results.add(i);
		}
		
		return results;
	}
}
