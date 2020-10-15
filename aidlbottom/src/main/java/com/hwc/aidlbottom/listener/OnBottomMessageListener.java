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
    default void onMessage(byte[] bytes) {

    }

    /**
     * 专车专用消息
     *
     * @param isPrivateCarSpecialInterface 是否在专车专用界面
     */
    default void onPrivateCarSpecial(boolean isPrivateCarSpecialInterface) {

    }

}
