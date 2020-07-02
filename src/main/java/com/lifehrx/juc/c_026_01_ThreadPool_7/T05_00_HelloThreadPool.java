package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * �ֶ����̳߳�
 *
 * Task
 *
 * ���￪���ֲ᣺�涨����new Tread Ҫͨ���̳߳أ������̡߳�
 *
 * �ڡ�����Ͱ�java�����ֲᡷ��ָ�����߳���Դ����ͨ���̳߳��ṩ����������Ӧ����������ʾ�Ĵ����̣߳�
 * ����һ�������̵߳Ĵ������ӹ淶�����Ժ�����ƿ����̵߳���������һ�����̵߳�ϸ�ڹ������̳߳ش���
 * �Ż�����Դ�Ŀ��������̳߳ز�����ʹ��Executorsȥ��������Ҫͨ��ThreadPoolExecutor��ʽ��
 * ��һ����������jdk��Executor�����Ȼ�ṩ����newFixedThreadPool()��newSingleThreadExecutor()��
 * newCachedThreadPool()�ȴ����̳߳صķ�����������������ԣ�������
 * ��������ǰ�漸�ַ����ڲ�Ҳ��ͨ��ThreadPoolExecutor��ʽʵ�֣�
 * ʹ��ThreadPoolExecutor�����ڴ����ȷ�̳߳ص����й��򣬴��������Լ���ҵ�񳡾���Ҫ���̳߳أ�������Դ�ľ��ķ��ա�
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
                // ����
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
        // 7������ �� ��������
        // 1: corePoolSize: �����߳��� 2��maximumPoolSize :����߳��� 3��keepAliveTime:����ʱ��
        // 4: ��λ 5���̶߳��� 6���̹߳��� 7������
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                // ArrayBlockingQueue : �Ĵ�С��Integer�� ���ֵ ��������к�����װ�������ﾩ����������
                new ArrayBlockingQueue<Runnable>(4),
                // defaultThreadFactory()
                Executors.defaultThreadFactory(),
                // (��ͬ��4��)�ܾ����ԣ��̳߳�æ �� ���������������ʵ������¶����Զ�����ԣ����￪���ֲᣩ
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
