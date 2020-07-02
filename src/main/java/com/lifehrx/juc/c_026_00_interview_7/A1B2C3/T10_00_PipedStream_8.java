package com.lifehrx.juc.c_026_00_interview_7.A1B2C3;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 很少用PipedStream
 *
 * 两个线程之间建立一个虚拟的管道 ,效率很低.面试很少考。
 * 两个通道来回写。和socket通道不一样（1个）。
 */
public class T10_00_PipedStream_8 {

    public static void main(String[] args) throws Exception {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        // 互相之间扔 消息玩儿（像扔球一样你扔给我我再扔回去）
        String msg = "Your Turn";


        new Thread(() -> {

            byte[] buffer = new byte[9];

            try {
                for(char c : aI) {
                    input1.read(buffer);

                    if(new String(buffer).equals(msg)) {
                        System.out.print(c);
                    }

                    output1.write(msg.getBytes());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }, "t1").start();

        new Thread(() -> {

            byte[] buffer = new byte[9];

            try {
                for(char c : aC) {

                    System.out.print(c);

                    output2.write(msg.getBytes());

                    input2.read(buffer);

                    if(new String(buffer).equals(msg)) {
                        continue;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }, "t2").start();
    }
}


