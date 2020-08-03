package com.hwc.aidlbottom.utils;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.hwc.aidlbottom.bean.MessageBean;
import com.hwc.aidlbottom.listener.OnBottomMessageListener;

import org.qiyi.video.svg.Andromeda;
import org.qiyi.video.svg.broadcast.BroadcastUtil;
import org.qiyi.video.svg.callback.BaseCallback;
import org.qiyi.video.svg.config.DispatcherConstants;
import org.qiyi.video.svg.listener.OnBroadcastListener;
import org.qiyi.video.svg.transfer.RemoteTransfer;
import org.qiyi.video.svg.utils.BaseProcessUtil;

import aidl.module.bottom.BottomMessage;
import aidl.module.bottom.IBottomMessenger;

/**
 * @author hwc
 */
public class BottomMessengerUtil extends BaseProcessUtil {

    private OnBottomMessageListener onMessageListener;

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


    @Override
    public <T extends IBinder> void registerSpeechRemoteService(Class serviceClass, T stubBinder) {
        registerRemoteService(true, DispatcherConstants.AUTHORITY_BOTTOM_MESSAGE, serviceClass, stubBinder);
    }

    @Override
    public void startMonitorRestart() {
        super.startMonitorRestart();
        BroadcastUtil.getInstance().setOnBroadcastListener(new OnBroadcastListener() {
            @Override
            public void onExtra(String s) {
                if (TextUtils.equals(s, DispatcherConstants.AUTHORITY_BOTTOM_MESSAGE)) {
                    if (onMessageListener != null) {
                        register(onMessageListener);
                    }
                }
            }
        });
    }

    @Override
    public void stopMonitorRestart() {
        super.stopMonitorRestart();
        BroadcastUtil.getInstance().setOnBroadcastListener(null);
    }

    /**
     * 注册监听底层发送上来的消息事件
     *
     * @param onBottomMessageListener
     * @return
     */
    public boolean register(final OnBottomMessageListener onBottomMessageListener) {
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
        this.onMessageListener = onBottomMessageListener;
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.register(new BaseCallback() {

                    @Override
                    public void onSucceed(Bundle result) {
                        MessageBean messageBean = (MessageBean) result.getSerializable("MessageBean");
                        if (onMessageListener != null) {
                            onMessageListener.message(messageBean);
                        }
                        org.qiyi.video.svg.log.Logger.d("got remote service with callback in other process(:banana),messageBean: " + messageBean);
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
     * 查询获取didiplay版本号
     *
     * @return
     */
    public int getDidiPlayVersion() {
        if (null == context) {
            Log.d(TAG, "BottomMessengerUtil Not init Context Is Null");
            return -1;
        }
        RemoteTransfer.getInstance().setCurrentAuthority(DispatcherConstants.AUTHORITY_BOTTOM_MESSAGE);
        IBinder iBottomMessenger = Andromeda.with(context).getRemoteService(IBottomMessenger.class);
        if (null == iBottomMessenger) {
            Log.d(TAG, "iBottomMessenger is Null");
            return -1;
        }
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                return buyApple.getDidiPlayVersion();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * 注销事件
     *
     * @return
     */
    public boolean unregister() {
        onMessageListener = null;
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
     *
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


    /**
     * 系统是否启动麦克风消息
     *
     * @return
     */
    public boolean isStartSystemMic() {
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
                buyApple.isStartSystemMic();
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 切换麦克风声道
     *
     * @return
     */
    public boolean switchMicrophoneChannel() {
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
                buyApple.switchMicrophoneChannel();
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 恢复声道
     *
     * @return
     */
    public boolean restoreChannel() {
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
                buyApple.restoreChannel();
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
