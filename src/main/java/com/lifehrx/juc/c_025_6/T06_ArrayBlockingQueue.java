package com.lifehrx.juc.c_025_6;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 115:00 - 118:00
 *
 * 有界限的 Queue
 */
public class T06_ArrayBlockingQueue {

	// Queue 的大小为10
	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {

		// 向队列里面添加10个元素
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
			System.out.println("a" + i);
		}

		// 到这步就没有办法添加进去了第11个了, 因为满了
		//strs.put("aaa"); //满了就会等待(等待消费者来消费)，程序阻塞
		//strs.add("aaa"); // 会抛异常

		//strs.offer("aaa"); // 不抛异常
		strs.offer("aaa", 1, TimeUnit.SECONDS); // 等1s钟, 看能不能加进去
		
		System.out.println(strs);
	}
}
