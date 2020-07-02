package com.lifehrx.juc.c_020_01_3;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phase �� �׶�
 * Phaser : �׶ι������Ǻ��ٻ��ʵ�������Լ�д���̳���
 * ������ά�����׶γ�Ա�����������磺��ǰ�߳�ִ�е� 0�׶� 1�׶� 2�׶�
 * ��ͬ�߳��ߵĽ׶β�ͬ������ĳ���׶�Ҫ�����߳�һ�����
 * �������Ŵ��㷨������ĵģ�
 *
 */
public class T08_TestPhaser {

    /**
     * ����ĳ�����
     *
     */
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            //˯ 1s �������
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // ����α�
        phaser.bulkRegister(5);

        for(int i=0; i<5; i++) {
            final int nameIndex = i;

            // ÿ�����Ͷ����⼸����Ϊ�������ÿ���׶�1.Ҫ���Ʋ�ͬ���ˣ�����ֻ��������ˣ� 2.���ܿ�Խ�׶Σ������ͽ������˲��У�
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
     * ģ�������������
     */
    static class MarriagePhaser extends Phaser {
        // 1.���е��߳�Ҫ�����˵�һ����դ��������������onAdvance()�Զ�����
        // 2.  phase �ڼ����׶�   registeredParties�� Ŀǰ�м����˲μ� ��phaser.bulkRegister(5); ��դ���ĸ�����դ���ϵȴ��̵߳ĸ�����
        // 3. return true; ������

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("�׶�1�������˵����ˣ�");
                    return false;
                case 1:
                    System.out.println("�׶�2�������˳����ˣ�");
                    return false;
                case 2:
                    System.out.println("�׶�3���������뿪�ˣ�");
                    System.out.println("�׶�4�����������");
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
            System.out.printf("%s �����ֳ���\n", name);
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s ����!\n", name);
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s �뿪��\n", name);
        }

    }
}


