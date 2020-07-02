/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 */
package com.lifehrx.juc.c_024_FromVectorToQueue_6;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {

	// 1. new 一个容器 （问题：多线程访问一个容器但是没加锁， 每个线程都发现了票数>0 都同时往外卖）
	static List<String> tickets = new ArrayList<>();

	// 2. 里面装 10万张
	static {
		for(int i=0; i<10000; i++) tickets.add("票编号：" + i);
	}

	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			new Thread(()->{
				// 3. 有票就一直取 remove
				while(tickets.size() > 0) {
					System.out.println("销售了--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
