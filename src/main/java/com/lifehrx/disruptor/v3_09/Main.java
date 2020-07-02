package com.lifehrx.disruptor.v3_09;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * lamda写法
 */

public class Main {
    public static void main(String[] args) {
        int bufferSize = 1024;

        // 1. Construct the Disruptor ： 直接Factory都省略了 lamda写法
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        // 2. Connect the handler ： handleEventsWith
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println(event.getValue()));

        // 3. Start the Disruptor, starts all threads running
        disruptor.start();

        // 4. Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++)
        {
            bb.putLong(0, l);

            // 5. publishEvent (lamda, lamda的值)
            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);

            // 2个参数的
            //ringBuffer.publishEvent((event, sequence, l1, l2) -> event.set(long(l1 + l2)), 10000L， 10000L);

            //ringBuffer.publishEvent((event, sequence) -> event.setValue(bb.getLong(0)));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
