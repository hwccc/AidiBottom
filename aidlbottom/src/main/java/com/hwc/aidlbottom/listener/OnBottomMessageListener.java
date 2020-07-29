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
    default void message(MessageBean messageBean) {

    }

    /**
     * 查询didiplay版本号
     *
     * @param didiPlayVersion
     */
    default void searchDidiPlayVersion(int didiPlayVersion) {

    }

}
