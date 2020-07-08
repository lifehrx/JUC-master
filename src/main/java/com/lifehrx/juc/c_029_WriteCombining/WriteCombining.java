package com.lifehrx.juc.c_029_WriteCombining;

/**
 * JVM + JUC 两部分知识
 *
 * 伪共享问题： 缓存行这种设计是硬件上的设计都是按块读取的。这样的效率更高。
 * cache line 缓存行 ：一行理解为一个64字节（64*8位）的数组。
 *
 * 合并写： 写指令的乱序执行
 * 老外写的这个 demo
 * 作为扩展
 * WriteCombining ： WC buffer 合并写（写操作也可以合并） 比 L1 速度更快
 * 只有4位
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
     * 从实验结果看2的效率更高：因为充分利用了合并写机制
     *
     * CPU 执行时候每次都填4个（每次都填满4个）, 比6个分为两组每次都先填满4个一组后，再等着后2个在下一次执行的快（没填满4个就要等的填满）
     */
    public static void main(final String[] args) {

        // 实验3次看结果。为了防止一次的偶然性
        for (int i = 1; i <= 3; i++) {
            System.out.println("第" + i + "执行用时：" + " SingleLoop duration (ns) = " + runCaseOne());
            System.out.println("第" + i + "执行用时：" + " SplitLoop  duration (ns) = " + runCaseTwo());
            System.out.println();
        }
    }

    //
    public static long runCaseOne() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        // 循环1 ： 同时改了6个位置（一个位只有1个字节）
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i; // b 可能会占一个位置
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

        // 循环2：先改前3个位置
        while (--i != 0) {
            int slot = i & MASK;
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
        }
        i = ITERATIONS;
        // 循环3：改后3个位置
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
