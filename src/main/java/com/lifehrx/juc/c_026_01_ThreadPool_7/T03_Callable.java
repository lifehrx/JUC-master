package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.*;

/**
 * Callable : 就是为了线程池使用的
 * 对Runnable进行了扩展
 * 对Callable的调用，可以有返回值
 *
 * Callable 类似于 Runnable
 *
 * call() 可以返回值 相当于 run() 返回值是void
 */
public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 一个任务： c
        Callable<String> c = new Callable() {
            @Override
            public String call() throws Exception {
                // 能返回结果很重要
                return "Hello Callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        //  把Callable 的任务c 扔到一个线程池
        //  submit(c) 提交一个任务时返回值是Future （把执行完的结果放到Future）
        Future<String> future = service.submit(c); //异步 ：主线程该干嘛干嘛

        //阻塞 ： 怎么能知道未来future这个对象有结果，调用future.get()，直到有结果了就返回回来
        System.out.println(future.get());

        service.shutdown();
    }

    // 自己练习没有任何用的代码
    public void test() throws ExecutionException, InterruptedException {
        Callable callable = new Callable() {
            @Override
            public String call() throws Exception {
                return "test Callable";
            }
        };

        ExecutorService es = Executors.newCachedThreadPool();
        Future<String> future = es.submit(callable);
        System.out.println(future.get());
        // 启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
        es.shutdown();
    }


}
