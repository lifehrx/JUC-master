package com.lifehrx.disruptor.v1_09;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        //Executor executor = Executors.newCachedThreadPool();

        // ��ʼ������
        LongEventFactory factory = new LongEventFactory();

        //must be power of 2 ���Ĵ�С
        int ringBufferSize = 1024;

        // factory �� ����
        // ringBufferSize : ���Ĵ�С
        // Executors.defaultThreadFactory() : ����������ʱ���̵߳Ĳ���
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, Executors.defaultThreadFactory());

        // ����handle ȥ����
        disruptor.handleEventsWith(new LongEventHandler());

        // start() ����֮�������ˣ� ����λ�ö��ж����ˣ�����������ˡ�Ȼ��ȴ������ߵĵ�����
        // ���׷��Ч�ʵļ���,һ���Գ�ʼ���ã��������жϡ�
        disruptor.start();

        // ȡ�������е��¼�
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
