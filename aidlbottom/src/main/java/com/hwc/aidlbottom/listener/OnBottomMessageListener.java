package com.hwc.aidlbottom.listener;

import com.hwc.aidlbottom.bean.MessageBean;

/**
 * @author hwc
 */
public interface OnBottomMessageListener {

    /**
     * 底层消息回调
     *
     * @param messageBean
     */
    void message(MessageBean messageBean);

}
