package com.lifehrx.juc.c_026_00_interview_7.A1B2C3;

/**
 * ×ÖÄ¸ÏÈÊä³ö
 *
 *A1B2C3D4E5F6G7
 */
public class T07_00_sync_wait_notify_3 {

    private static volatile boolean t2Started = false;

    //private static CountDownLatch latch = new C(1);

    public static void main(String[] args) {
        final Object o = new Object();



        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{
            //latch.await();

            synchronized (o) {

                while(!t2Started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //

                for(char c : aI) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();
            }
        }, "t1").start();

        new Thread(()->{

            synchronized (o) {
                for(char c : aC) {
                    System.out.print(c);
                    //latch.countDown()
                    t2Started = true;
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }
}


