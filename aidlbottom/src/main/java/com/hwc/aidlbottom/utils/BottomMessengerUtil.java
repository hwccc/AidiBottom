package com.hwc.aidlbottom.utils;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.hwc.aidlbottom.listener.OnBottomMessageListener;

import org.qiyi.video.svg.Andromeda;
import org.qiyi.video.svg.callback.BaseCallback;
import org.qiyi.video.svg.config.DispatcherConstants;
import org.qiyi.video.svg.transfer.RemoteTransfer;

import aidl.module.bottom.BottomMessage;
import aidl.module.bottom.IBottomMessenger;

/**
 * @author hwc
 */
public class BottomMessengerUtil extends BaseProcessUtil {

    private volatile static BottomMessengerUtil instance;

    public static BottomMessengerUtil getInstance() {
        if (instance == null) {
            synchronized (BottomMessengerUtil.class) {
                if (instance == null) {
                    instance = new BottomMessengerUtil();
                }
            }
        }
        return instance;
    }

    private BottomMessengerUtil() {

    }


    /**
     * 注册监听底层发送上来的消息事件
     * @param onBottomNewsListener
     * @return
     */
    public boolean register(final OnBottomMessageListener onBottomNewsListener) {
        if (null == context) {
            Log.d(TAG, "BottomMessengerUtil Not init Context Is Null");
            return false;
        }
        RemoteTransfer.getInstance().setCurrentAuthority(DispatcherConstants.AUTHORITY_BOTTOM_MESSAGE);
        IBinder iBottomMessenger = Andromeda.with(context).getRemoteService(IBottomMessenger.class);
        if (null == iBottomMessenger) {
            Log.d(TAG, "iBottomMessenger is Null");
            return false;
        }
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.register(new BaseCallback() {

                    @Override
                    public void onSucceed(Bundle result) {
                        BottomMessage bottomMessage = new BottomMessage();
                        bottomMessage.bottomBytes = result.getByteArray("bottomBytes");
                        bottomMessage.bottomByte = result.getByte("bottomByte");
                        if (onBottomNewsListener != null) {
                            onBottomNewsListener.message(bottomMessage);
                        }
                        org.qiyi.video.svg.log.Logger.d("got remote service with callback in other process(:banana),bottomMessage: " + bottomMessage);
                    }

                    @Override
                    public void onFailed(String reason) {
                        org.qiyi.video.svg.log.Logger.e("buyAppleOnNet failed,reason:" + reason);
                    }
                });
                return true;
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 注销事件
     * @return
     */
    public boolean unregister() {
        if (null == context) {
            Log.d(TAG, "BottomMessengerUtil Not init Context Is Null");
            return false;
        }
        RemoteTransfer.getInstance().setCurrentAuthority(DispatcherConstants.AUTHORITY_BOTTOM_MESSAGE);
        IBinder iBottomMessenger = Andromeda.with(context).getRemoteService(IBottomMessenger.class);
        if (null == iBottomMessenger) {
            Log.d(TAG, "iBottomMessenger is Null");
            return false;
        }
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.unregister();
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 发送消息
     * @param bottomMessage
     * @return
     */
    public boolean send(BottomMessage bottomMessage) {
        if (null == context) {
            Log.d(TAG, "BottomMessengerUtil Not init Context Is Null");
            return false;
        }
        RemoteTransfer.getInstance().setCurrentAuthority(DispatcherConstants.AUTHORITY_BOTTOM_MESSAGE);
        IBinder iBottomMessenger = Andromeda.with(context).getRemoteService(IBottomMessenger.class);
        if (null == iBottomMessenger) {
            Log.d(TAG, "iBottomMessenger is Null");
            return false;
        }
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.send(bottomMessage);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
