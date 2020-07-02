/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 * 
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * 
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 * 
 * 使用ConcurrentQueue提高并发性
 *
 */
package com.lifehrx.juc.c_024_FromVectorToQueue_6;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {

	// 1. Queue 容器 ： 以后多线程多考虑用Queue（元素不重复） , 少考虑 List
	// ConcurrentLinkedQueue 源码是用CAS实现的效率较高
	// sync 和 CSA 要看并发量多少, 比较效率
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	
	static {
		// 2. 1000 张票
		for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{

				// 3. 售货员一直在卖票
				while(true) {
					// poll ： s 声明在这里面没有问题， 放在外面就会有问题，因为是公共的资源
					String s = tickets.poll();
					// 4. 当某一时刻一伸手取,发现没票了, 不卖了
					if(s == null) break;
					else System.out.println("销售了--" + s);
				}
			}).start();
		}
	}
}
