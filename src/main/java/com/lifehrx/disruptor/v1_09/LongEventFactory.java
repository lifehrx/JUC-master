package com.lifehrx.disruptor.v1_09;

import com.lmax.disruptor.EventFactory;

/**
 * EventFactory : ���� ���ڲ���һ���¼� Event e
 *
 * ��Ϊ���� new �������Խ��� GCƵ�ʡ�
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    public LongEvent newInstance() {
        return new LongEvent();
    }
}
