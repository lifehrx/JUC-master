package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.*;

public class T14_MyRejectedHandler {

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new MyHandler());
    }

    //策略： 举例根据自己的业务逻辑来定义
    // RejectedExecutionHandler : 无法执行的任务的处理程序
    static class MyHandler implements RejectedExecutionHandler {

        // 自定义
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")

            // 业务逻辑： 如存储到什么数据库中
            //save r kafka mysql redis
            //try 3 times
            // 如果有空了
            if(executor.getQueue().size() < 10000) {
                //try put again();
            }
        }
    }
}
