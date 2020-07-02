package com.lifehrx.juc.c_000_thread;

/**
 * 线程 sleep(500) : 睡500毫秒，让给其它线程在CPU上面运行
 *
 *
 */
public class T03_Sleep_Yield_Join {

    public static void main(String[] args) {
        testSleep();
       //testYield();
        //testJoin();
    }

    /**
     * 1. sleep():睡醒之后自动运行，就绪状态
     */
    static void testSleep() {
        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 2. yield() : 谦让的退出CPU一小会儿,回到等待队列中
     * 其他线程是有机会来CPU,别的线程能不能抢到不管
     * 开发中很少用，压力测试可能会用到
     *
     * 前线程从“运行状态”进入到“就绪状态”
     */
    static void testYield() {

        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("A" + i);
                if(i%10 == 0) Thread.yield();


            }
        }).start();

        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("------------B" + i);
                if(i%10 == 0) Thread.yield();
            }
        }).start();
    }

    /**
     * 3. join() : 加入
     *
     * 在T2里面用t1.join()这样才有意义: t1 调到 t2 上去运行，运行完再回来
     * 用于等待另一个线程的结束（和谁调用谁有关系的）
     *
     * 应用场景：使得线程 t1->t2->t3 按顺序执行
     *
     *
     * 把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程
     * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
     */
    static void testJoin() {

        Thread t1 = new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i=0; i<100; i++) {
                System.out.println("B" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
