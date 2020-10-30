package com.hwc.aidlbottom.listener;

/**
 * @author hwc
 */
public interface OnBottomMicListener {

    /**
     * 底层麦克风状态
     *
     * @param isOpen
     */
    default void onBottomMicStatus(boolean isOpen) {

    }

}
