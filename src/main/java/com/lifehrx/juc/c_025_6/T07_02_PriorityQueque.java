package com.lifehrx.juc.c_025_6;

import java.util.PriorityQueue;

/**
 * PriorityQueque :
 *
 * �ڲ�ʵ����һ������ �� �������Ķ�����
 */
public class T07_02_PriorityQueque {

    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");

        // q.size() ÿѭ��һ�ξͻ�� ������ֱ�ӹ̶�ס
        for (int i = 0; i < 5; i++) {
            // ��С������� �� a c d e z
            System.out.println("q.poll() = " + q.poll());
        }

    }
}
