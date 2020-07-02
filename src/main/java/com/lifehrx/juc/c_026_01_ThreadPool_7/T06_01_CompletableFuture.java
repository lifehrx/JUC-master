package com.lifehrx.juc.c_026_01_ThreadPool_7;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 面试一般不会问 ： 但是很好用
 *
 * 假设你能够提供一个服务
 * 这个服务查询各大电商网站同一类产品的价格并汇总展示
 *
 * 解决了各个线程反复的get()和if
 */
public class T06_01_CompletableFuture {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start, end;

        /**
         * 法1： 一个一个 10s
         * 如果其中一个平台取数时候断了，但是三个平台要一起返回值
         */
        /*start = System.currentTimeMillis();

        priceOfTM();
        priceOfTB();
        priceOfJD();

        end = System.currentTimeMillis();
        System.out.println("use serial method call! " + (end - start));*/

        start = System.currentTimeMillis();

        /**
         * 法二：
         * 使用场景：组合很多任务最终返回一个结果
         *          同一个商品不同店家的价格比较
         */
        // Async 异步： CompletableFuture.supplyAsync(()->priceOfTM()) 天猫的异步任务
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(()->priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(()->priceOfJD());

        //  CompletableFuture 用于各种任务的管理： 管理3个Future （3个结果都一起回来了）
        // allof()：所有任务  anyof():某一个任务回来了结束如论文查重
        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();


        // 拿到 天猫返回数据
        CompletableFuture.supplyAsync(()->priceOfTM())
                // Double转成String
                .thenApply(String::valueOf)
                // String拼接
                .thenApply(str-> "天猫 price " + str)
                // 打印
                .thenAccept(System.out::println);


        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   // 获取天猫平台价格
    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    // 淘宝
    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    // 京东
    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    /*private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }*/


    // 模拟打印取数的时间延时
    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
