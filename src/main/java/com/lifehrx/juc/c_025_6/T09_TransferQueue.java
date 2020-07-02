package com.lifehrx.juc.c_025_6;

import java.util.concurrent.LinkedTransferQueue;

/**
 * TransferQueue : 传递
 *
 * 使用场景 ： 要看到结果
 * 1.订单看到付钱了才算完(但是现在用用MQ实现，不用自己写)
 * 2.交替打印
 *
 */
public class T09_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
		
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		// 重点 ： put()装完就走了 区别 transfer()装完会等着有人把它取走
		strs.transfer("aaa");
		
		/*strs.put("aaa");
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
	}
}
