package com.hwc.aidlbottom.listener;

/**
 * @author hwc
 */
public interface OnMessageListener {


    /**
     * 消息
     *
     * @param message
     */
    default void onMessage(String message) {

    }

}
