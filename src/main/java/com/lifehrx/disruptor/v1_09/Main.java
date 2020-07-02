package com.lifehrx.disruptor.v1_09;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        //Executor executor = Executors.newCachedThreadPool();

        // 初始化工厂
        LongEventFactory factory = new LongEventFactory();

        //must be power of 2 环的大小
        int ringBufferSize = 1024;

        // factory ： 工厂
        // ringBufferSize : 环的大小
        // Executors.defaultThreadFactory() : 调用消费者时候线程的产生
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, Executors.defaultThreadFactory());

        // 调用handle 去处理
        disruptor.handleEventsWith(new LongEventHandler());

        // start() 调用之后环起来了： 各个位置都有东西了，东西分配好了。然后等待消费者的到来。
        // 如果追求效率的极致,一次性初始化好，不必再判断。
        disruptor.start();

        // 取出数组中的事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for(long l = 0; l<100; l++) {
            bb.putLong(0, l);

            producer.onData(bb);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        disruptor.shutdown();
    }
}
