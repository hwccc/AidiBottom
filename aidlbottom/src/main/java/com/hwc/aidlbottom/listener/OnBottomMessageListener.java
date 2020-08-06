package com.hwc.aidlbottom.listener;

/**
 * @author hwc
 */
public interface OnBottomMessageListener {


    /**
     * 按键消息
     *
     * @param bytes
     */
    default void onButtonsMessage(byte[] bytes) {

    }


    /**
     * 按键学习消息
     *
     * @param bytes
     */
    default void onButtonsLearningMessage(byte[] bytes) {

    }


    /**
     * 其它底层消息（非按键、非按键学习消息）
     *
     * @param bytes
     */
    void onMessage(byte[] bytes);

}
