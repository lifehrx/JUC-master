package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.*;

/**
 * Callable : ����Ϊ���̳߳�ʹ�õ�
 * ��Runnable��������չ
 * ��Callable�ĵ��ã������з���ֵ
 *
 * Callable ������ Runnable
 *
 * call() ���Է���ֵ �൱�� run() ����ֵ��void
 */
public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // һ������ c
        Callable<String> c = new Callable() {
            @Override
            public String call() throws Exception {
                // �ܷ��ؽ������Ҫ
                return "Hello Callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        //  ��Callable ������c �ӵ�һ���̳߳�
        //  submit(c) �ύһ������ʱ����ֵ��Future ����ִ����Ľ���ŵ�Future��
        Future<String> future = service.submit(c); //�첽 �����̸߳ø������

        //���� �� ��ô��֪��δ��future��������н��������future.get()��ֱ���н���˾ͷ��ػ���
        System.out.println(future.get());

        service.shutdown();
    }

    // �Լ���ϰû���κ��õĴ���
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
        // ��������رգ�������ǰ�ύ�����񽫱�ִ�У�����������κ�������
        es.shutdown();
    }


}
