
package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ��ʶExecutorService,�Ķ�API�ĵ�
 * ��ʶsubmit��������չ��execute����������һ������ֵ
 *
 * submit() : �첽���ύ����ֵ��ԭ�̸߳���ôִ�о���ôִ��
 */
public class T02_ExecutorService  {
    public static void main(String[] args) {

        ExecutorService e = Executors.newCachedThreadPool();

    }
}
