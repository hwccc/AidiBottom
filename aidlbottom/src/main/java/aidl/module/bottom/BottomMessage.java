package aidl.module.bottom;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * 底层消息数据
 *
 * @author hwc
 */
public class BottomMessage implements Parcelable {

    /**
     * 底层消息类型
     */
    public String msgType;

    /**
     * byte底层消息
     */
    public byte bottomByte;

    /**
     * byte数组底层消息
     */
    public byte[] bottomBytes;


    public BottomMessage() {

    }

    protected BottomMessage(Parcel in) {
        bottomByte = in.readByte();
        bottomBytes = in.createByteArray();
        msgType = in.readString();
    }

    public static final Creator<BottomMessage> CREATOR = new Creator<BottomMessage>() {
        @Override
        public BottomMessage createFromParcel(Parcel in) {
            return new BottomMessage(in);
        }

        @Override
        public BottomMessage[] newArray(int size) {
            return new BottomMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(bottomByte);
        parcel.writeByteArray(bottomBytes);
        parcel.writeString(msgType);
    }

    @Override
    public String toString() {
        return "BottomMessage{" +
                "bottomByte=" + bottomByte +
                ", bottomBytes=" + Arrays.toString(bottomBytes) +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
