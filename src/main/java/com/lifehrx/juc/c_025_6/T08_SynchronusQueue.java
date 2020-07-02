package com.lifehrx.juc.c_025_6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 133 ； 00
 * SynchronusQueue ： 同步Queue
 * 容量为0 不是装东西的。
 * 通知的作用
 * 两个线程： t1要给t2线程 递东西， 相当于手把手的递过去，中间不存在队列暂时存放这个东西
 * 这线程没有就一直等着，什么时候线程来了，再直接放到对方手里。
 * 相当于：两个线程交换数据  （比exchange 简单）
 */
public class T08_SynchronusQueue { //容量为0
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		//strs.put("aaa"); //阻塞等待消费者消费
		//strs.put("bbb");
		//strs.add("aaa"); // 报错 ： Exception in thread "main" java.lang.IllegalStateException: Queue full
		System.out.println("strs.size() = " + strs.size());
	}
}
