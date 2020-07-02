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
			// ��� �� true / false
			strs.offer("a" + i);  //offer() == add() �����������ȼ�, ������offer() �Ƚ϶�, ԭ����add() �Ӳ���ȥʱ������쳣,offer() ֻ�᷵��false
		}
		System.out.println("==================== offer() ======================");
		System.out.println(strs);
		System.out.println(strs.size());
		System.out.println();

		// poll() ȡ ��remove
		System.out.println("==================== poll() ======================");
		System.out.println(strs.poll());
		System.out.println(strs.size());
		System.out.println();

		//peek() ȡ ����remove
		System.out.println("==================== peek() ======================");
		System.out.println(strs.peek());
		System.out.println(strs.size());
		System.out.println();
		
		//˫�˶���Deque
	}
}
