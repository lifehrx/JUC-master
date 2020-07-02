package com.lifehrx.juc.c_025_6;

import java.util.PriorityQueue;

/**
 * PriorityQueque :
 *
 * 内部实现了一种排序 ： 二叉树的堆排序
 */
public class T07_02_PriorityQueque {

    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");

        // q.size() 每循环一次就会变 ，所以直接固定住
        for (int i = 0; i < 5; i++) {
            // 从小到大输出 ： a c d e z
            System.out.println("q.poll() = " + q.poll());
        }

    }
}
