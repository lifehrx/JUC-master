package com.lifehrx.juc.c_022_RefTypeAndThreadLocal_5;

import java.io.IOException;

/**
 * ǿ����
 */
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {

        T05_M m = new T05_M();

        // m û�������� ���������ǻᱻ����
        m = null;
        System.gc(); //DisableExplicitGC

        // ������ǰ�߳�
        System.in.read();
    }
}
