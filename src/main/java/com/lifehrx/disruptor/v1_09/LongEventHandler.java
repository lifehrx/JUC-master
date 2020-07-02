package com.lifehrx.disruptor.v1_09;

import com.lmax.disruptor.EventHandler;

/**
 * LongEventHandler 消息的处理 ：
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     *
     * @param longEvent : 要处理的消息
     * @param l         : 位置
     * @param b         : 是否是最后一个元素
     * @throws Exception
     */
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
