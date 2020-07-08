package com.lifehrx.juc.c_029_WriteCombining;

/**
 * JVM + JUC ������֪ʶ
 *
 * α�������⣺ ���������������Ӳ���ϵ���ƶ��ǰ����ȡ�ġ�������Ч�ʸ��ߡ�
 * cache line ������ ��һ�����Ϊһ��64�ֽڣ�64*8λ�������顣
 *
 * �ϲ�д�� дָ�������ִ��
 * ����д����� demo
 * ��Ϊ��չ
 * WriteCombining �� WC buffer �ϲ�д��д����Ҳ���Ժϲ��� �� L1 �ٶȸ���
 * ֻ��4λ
 *
 */
public final class WriteCombining {

    private static final int ITERATIONS = Integer.MAX_VALUE;
    private static final int ITEMS = 1 << 24;
    private static final int MASK = ITEMS - 1;

    private static final byte[] arrayA = new byte[ITEMS];
    private static final byte[] arrayB = new byte[ITEMS];
    private static final byte[] arrayC = new byte[ITEMS];
    private static final byte[] arrayD = new byte[ITEMS];
    private static final byte[] arrayE = new byte[ITEMS];
    private static final byte[] arrayF = new byte[ITEMS];

    /**
     *
     * ��ʵ������2��Ч�ʸ��ߣ���Ϊ��������˺ϲ�д����
     *
     * CPU ִ��ʱ��ÿ�ζ���4����ÿ�ζ�����4����, ��6����Ϊ����ÿ�ζ�������4��һ����ٵ��ź�2������һ��ִ�еĿ죨û����4����Ҫ�ȵ�������
     */
    public static void main(final String[] args) {

        // ʵ��3�ο������Ϊ�˷�ֹһ�ε�żȻ��
        for (int i = 1; i <= 3; i++) {
            System.out.println("��" + i + "ִ����ʱ��" + " SingleLoop duration (ns) = " + runCaseOne());
            System.out.println("��" + i + "ִ����ʱ��" + " SplitLoop  duration (ns) = " + runCaseTwo());
            System.out.println();
        }
    }

    //
    public static long runCaseOne() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        // ѭ��1 �� ͬʱ����6��λ�ã�һ��λֻ��1���ֽڣ�
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i; // b ���ܻ�ռһ��λ��
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
            arrayD[slot] = b;
            arrayE[slot] = b;
            arrayF[slot] = b;
        }
        return System.nanoTime() - start;
    }

    public static long runCaseTwo() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        // ѭ��2���ȸ�ǰ3��λ��
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
        }
        i = ITERATIONS;
        // ѭ��3���ĺ�3��λ��
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayD[slot] = b;
            arrayE[slot] = b;
            arrayF[slot] = b;
        }
        return System.nanoTime() - start;
    }
}
