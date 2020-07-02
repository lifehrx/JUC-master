
package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 认识ExecutorService,阅读API文档
 * 认识submit方法，扩展了execute方法，具有一个返回值
 *
 * submit() : 异步的提交返回值，原线程该这么执行就怎么执行
 */
public class T02_ExecutorService  {
    public static void main(String[] args) {

        ExecutorService e = Executors.newCachedThreadPool();

    }
}
