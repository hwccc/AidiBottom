package com.hwc.aidlbottom.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 底层消息数据
 *
 * @author hwc
 */
public class MessageBean implements Serializable {

    /**
     * 是否为旋钮操作
     */
    public boolean isKnob;

    /**
     * byte底层消息
     */
    public byte bottomByte;

    /**
     * byte数组底层消息
     */
    public byte[] bottomBytes;


    public MessageBean() {

    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "isKnob=" + isKnob +
                ", bottomByte=" + bottomByte +
                ", bottomBytes=" + Arrays.toString(bottomBytes) +
                '}';
    }
}
