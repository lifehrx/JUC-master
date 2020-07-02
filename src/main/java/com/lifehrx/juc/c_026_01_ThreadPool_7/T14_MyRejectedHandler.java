package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.*;

public class T14_MyRejectedHandler {

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new MyHandler());
    }

    //���ԣ� ���������Լ���ҵ���߼�������
    // RejectedExecutionHandler : �޷�ִ�е�����Ĵ������
    static class MyHandler implements RejectedExecutionHandler {

        // �Զ���
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")

            // ҵ���߼��� ��洢��ʲô���ݿ���
            //save r kafka mysql redis
            //try 3 times
            // ����п���
            if(executor.getQueue().size() < 10000) {
                //try put again();
            }
        }
    }
}
