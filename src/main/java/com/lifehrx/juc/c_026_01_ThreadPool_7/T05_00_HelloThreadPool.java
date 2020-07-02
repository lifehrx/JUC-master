package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 手动建线程池
 *
 * Task
 *
 * 阿里开发手册：规定不用new Tread 要通过线程池，创建线程。
 *
 * 在《阿里巴巴java开发手册》中指出了线程资源必须通过线程池提供，不允许在应用中自行显示的创建线程，
 * 这样一方面是线程的创建更加规范，可以合理控制开辟线程的数量；另一方面线程的细节管理交给线程池处理，
 * 优化了资源的开销。而线程池不允许使用Executors去创建，而要通过ThreadPoolExecutor方式，
 * 这一方面是由于jdk中Executor框架虽然提供了如newFixedThreadPool()、newSingleThreadExecutor()、
 * newCachedThreadPool()等创建线程池的方法，但都有其局限性，不够灵活；
 * 另外由于前面几种方法内部也是通过ThreadPoolExecutor方式实现，
 * 使用ThreadPoolExecutor有助于大家明确线程池的运行规则，创建符合自己的业务场景需要的线程池，避免资源耗尽的风险。
 */
public class T05_00_HelloThreadPool {

    static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            try {
                // 阻塞
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        // 7个参数 ： 爱考参数
        // 1: corePoolSize: 核心线程数 2：maximumPoolSize :最大线程数 3：keepAliveTime:持续时间
        // 4: 单位 5：线程队列 6：线程工厂 7：策略
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                // ArrayBlockingQueue : 的大小是Integer的 最大值 （这个队列很容易装满：阿里京东的描述）
                new ArrayBlockingQueue<Runnable>(4),
                // defaultThreadFactory()
                Executors.defaultThreadFactory(),
                // (不同的4种)拒绝策略：线程池忙 且 任务队列满（但是实际情况下都是自定义策略，阿里开发手册）
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }

        System.out.println(tpe.getQueue());

        tpe.execute(new Task(100));

        System.out.println(tpe.getQueue());

        tpe.shutdown();
    }
}
