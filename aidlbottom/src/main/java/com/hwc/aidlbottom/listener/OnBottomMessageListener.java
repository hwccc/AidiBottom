package com.hwc.aidlbottom.listener;

import aidl.module.bottom.BottomMessage;

/**
 * @author hwc
 */
public interface OnBottomMessageListener {

    /**
     * 底层消息回调
     *
     * @param bottomMessage
     */
    void message(BottomMessage bottomMessage);

}
