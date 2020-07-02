package com.lifehrx.juc.c_020_01_3;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phase ： 阶段
 * Phaser : 阶段管理（但是很少会问到这个类自己写工程程序）
 * 本身是维护“阶段成员”变量，比如：当前线程执行到 0阶段 1阶段 2阶段
 * 不同线程走的阶段不同，或是某个阶段要几个线程一起参与
 * 场景：遗传算法（达尔文的）
 *
 */
public class T08_TestPhaser {

    /**
     * 婚礼的场景：
     *
     */
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            //睡 1s 的随机数
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // 婚礼嘉宾
        phaser.bulkRegister(5);

        for(int i=0; i<5; i++) {
            final int nameIndex = i;

            // 每个宾客都是这几种行为，婚礼的每个阶段1.要控制不同的人（洞房只能这对新人） 2.不能跨越阶段（刚来就进洞房了不行）
            new Thread(()->{

                Person p = new Person("person " + nameIndex);
                p.arrive();
                phaser.arriveAndAwaitAdvance();

                p.eat();
                phaser.arriveAndAwaitAdvance();

                p.leave();
                phaser.arriveAndAwaitAdvance();
            }).start();
        }

    }

    /**
     * 模拟整个婚礼过程
     */
    static class MarriagePhaser extends Phaser {
        // 1.所有的线程要满足了第一个“栅栏”的条件调用onAdvance()自动调用
        // 2.  phase 第几个阶段   registeredParties； 目前有几个人参加 由phaser.bulkRegister(5); （栅栏的个数和栅栏上等待线程的个数）
        // 3. return true; 结束了

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("阶段1：所有人到齐了！");
                    return false;
                case 1:
                    System.out.println("阶段2：所有人吃完了！");
                    return false;
                case 2:
                    System.out.println("阶段3：所有人离开了！");
                    System.out.println("阶段4：婚礼结束！");
                    return true;
                default:
                    return true;
            }
        }
    }


    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
        }

    }
}


