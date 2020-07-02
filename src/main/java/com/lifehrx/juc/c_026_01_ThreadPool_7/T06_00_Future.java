package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.*;

/**
 * Future : δ����ִ����Ľ��
 *
 * FutureTask �����ã����
 * 	Future + Task  = ����һ����������һ��Future����Դ�룩
 * 	���Խ�ִ�к�Ķ���ֱ�Ӵ浽�Լ����������Callable�Ͳ���ҪFuture�������
 *
 * 	ʵ����2���ӿ� �� Runnable, Future<V>
 */
public class T06_00_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		}); // ��δ����൱�� new Callable () { Integer call();}
		
		new Thread(task).start();
		
		System.out.println(task.get()); //����


	}
}
