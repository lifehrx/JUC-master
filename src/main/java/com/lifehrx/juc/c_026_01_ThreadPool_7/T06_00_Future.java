package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.*;

/**
 * Future : 未来的执行完的结果
 *
 * FutureTask ：常用，灵活
 * 	Future + Task  = 即是一个任务又是一个Future（看源码）
 * 	可以将执行后的对象直接存到自己对象里。但是Callable就不行要Future对象接收
 *
 * 	实现了2个接口 ： Runnable, Future<V>
 */
public class T06_00_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		}); // 这段代码相当于 new Callable () { Integer call();}
		
		new Thread(task).start();
		
		System.out.println(task.get()); //阻塞


	}
}
