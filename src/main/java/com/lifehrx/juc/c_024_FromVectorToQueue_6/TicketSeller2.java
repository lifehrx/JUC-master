/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 *  
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？

 */
package com.lifehrx.juc.c_024_FromVectorToQueue_6;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {

	// 1. Vector 线程安全
	static Vector<String> tickets = new Vector<>();
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{

				// 问题 ： a. tickets.size() 加锁了
				while(tickets.size() > 0) {

					// 1000 张票, 睡10ms
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// 问题：b. tickets.remove() 也加锁了 但是中间的“睡眠”代码没有加锁, 睡眠期间同样会有很多线程去访问, 结果也是卖超了
					System.out.println("销售了--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
