package com.lifehrx.disruptor.v1_09;

import com.lmax.disruptor.EventHandler;

/**
 * LongEventHandler ��Ϣ�Ĵ��� ��
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     *
     * @param longEvent : Ҫ�������Ϣ
     * @param l         : λ��
     * @param b         : �Ƿ������һ��Ԫ��
     * @throws Exception
     */
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
