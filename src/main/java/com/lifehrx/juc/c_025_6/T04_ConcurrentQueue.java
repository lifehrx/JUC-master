package com.lifehrx.juc.c_025_6;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 100:00
 * ConcurrentQueue :
 *
 *
 */
public class T04_ConcurrentQueue {

	public static void main(String[] args) {

		Queue<String> strs = new ConcurrentLinkedQueue<>();
		
		for(int i=0; i<10; i++) {
			// 添加 ： true / false
			strs.offer("a" + i);  //offer() == add() 这两个方法等价, 现在用offer() 比较多, 原因是add() 加不进去时候会抛异常,offer() 只会返回false
		}
		System.out.println("==================== offer() ======================");
		System.out.println(strs);
		System.out.println(strs.size());
		System.out.println();

		// poll() 取 不remove
		System.out.println("==================== poll() ======================");
		System.out.println(strs.poll());
		System.out.println(strs.size());
		System.out.println();

		//peek() 取 并且remove
		System.out.println("==================== peek() ======================");
		System.out.println(strs.peek());
		System.out.println(strs.size());
		System.out.println();
		
		//双端队列Deque
	}
}
