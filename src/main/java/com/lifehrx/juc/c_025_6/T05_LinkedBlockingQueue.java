package com.lifehrx.juc.c_025_6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue : 阻塞队列 (链表实现的BlockingQueue) 生产者消费者模型
 *
 * 实现了自动阻塞当前线程 ： 用 park() 实现的阻塞 （await 的底层也是用park()实现）
 *
 * 无界队列：可以一直添加到内存溢出
 *
 * 在Queue 上新增的方法： 添加put() 取take()  如果加不进去了或取不出来了，线程会阻塞 await状态
 *
 *
 */
public class T05_LinkedBlockingQueue {

	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

	static Random r = new Random();

	public static void main(String[] args) {

		// 1. 装100个 a , 每装一个就睡1000ms
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {

					// 1. 新增方法put()
					strs.put("a" + i); //如果满了，就会等待
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "p1").start();

		// 2. 5个线程一直在队列里面取, 如果队列空了就等着, 如果有元素了就马上取出来
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (;;) {
					try {
						// 2. 新增方法take()
						System.out.println("线程名称 = " + Thread.currentThread().getName() + "  take() ->" + strs.take()); //如果空了，就会等待
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "c" + i).start();

		}
	}
}
