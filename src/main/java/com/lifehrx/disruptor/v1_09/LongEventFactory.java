package com.lifehrx.disruptor.v1_09;

import com.lmax.disruptor.EventFactory;

/**
 * EventFactory : 工厂 用于产生一个事件 Event e
 *
 * 因为不用 new 对象，所以降低 GC频率。
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    public LongEvent newInstance() {
        return new LongEvent();
    }
}
