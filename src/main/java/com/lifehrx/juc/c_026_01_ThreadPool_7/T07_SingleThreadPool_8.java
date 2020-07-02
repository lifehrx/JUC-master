package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T07_SingleThreadPool_8 {
	public static void main(String[] args) {
		/**
		 * ���е��̳߳ض����� ExecutorService �ϼ̳е�
		 *
		 * newSingleThreadExecutor() ���̵߳��̳߳�
		 * һ���̵߳��̳߳أ� ��֤�����˳��ִ��
		 *
		 * ���￪���ֲ᣺���鲻����JDK�Դ����̳߳أ���Ϊ������
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
