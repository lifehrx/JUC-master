package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.Executor;

/**
 * Executor : 执行者
 *
 * 定义和运行分开了
 */
public class T01_MyExecutor_6 implements Executor{

	public static void main(String[] args) {
		new T01_MyExecutor_6().execute(()->System.out.println("hello executor"));
	}

	@Override
	public void execute(Runnable command) {
		// 写法一 : new Thread().run()
		//new Thread(command).run();

		// 写法二 ： 直接run()
		command.run();
		
	}

}

