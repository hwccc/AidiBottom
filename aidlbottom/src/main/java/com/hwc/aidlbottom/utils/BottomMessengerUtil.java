package com.hwc.aidlbottom.utils;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.hwc.aidlbottom.bean.MessageBean;
import com.hwc.aidlbottom.config.BottomConfigure;
import com.hwc.aidlbottom.listener.OnBottomMessageListener;

import org.qiyi.video.svg.Andromeda;
import org.qiyi.video.svg.broadcast.BroadcastUtil;
import org.qiyi.video.svg.callback.BaseCallback;
import org.qiyi.video.svg.config.DispatcherConstants;
import org.qiyi.video.svg.listener.OnBroadcastListener;
import org.qiyi.video.svg.transfer.RemoteTransfer;
import org.qiyi.video.svg.utils.BaseProcessUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import aidl.module.bottom.BottomMessage;
import aidl.module.bottom.IBottomMessenger;

/**
 * @author hwc
 */
public class BottomMessengerUtil extends BaseProcessUtil {

    private Map<Long, OnBottomMessageListener> onBottomMessageListenerMap;

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
        onBottomMessageListenerMap = new HashMap<>();
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
                    if (onBottomMessageListenerMap != null) {
                        Iterator<Map.Entry<Long, OnBottomMessageListener>> it = onBottomMessageListenerMap.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<Long, OnBottomMessageListener> entry = it.next();
                            register(entry.getKey(), entry.getValue());
                        }
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
        return register(System.currentTimeMillis(), onBottomMessageListener);
    }


    /**
     * 注册监听底层发送上来的消息事件
     *
     * @param onBottomMessageListener
     * @return
     */
    private boolean register(long timeTag, final OnBottomMessageListener onBottomMessageListener) {
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
        onBottomMessageListenerMap.put(timeTag, onBottomMessageListener);
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.register(timeTag, new BaseCallback() {

                    @Override
                    public void onSucceed(Bundle result) {
                        MessageBean messageBean = (MessageBean) result.getSerializable("MessageBean");
                        if (onBottomMessageListenerMap != null && onBottomMessageListenerMap.containsValue(onBottomMessageListener)
                                && messageBean != null) {
                            if (TextUtils.equals(BottomConfigure.TYPE_BUTTONS, messageBean.msgType)) {
                                onBottomMessageListener.onButtonsMessage(messageBean.bottomBytes);
                            } else if (TextUtils.equals(BottomConfigure.TYPE_BUTTONS_LEARNING, messageBean.msgType)) {
                                onBottomMessageListener.onButtonsLearningMessage(messageBean.bottomBytes);
                            } else {
                                onBottomMessageListener.onMessage(messageBean.bottomBytes);
                            }
                        }
                        org.qiyi.video.svg.log.Logger.d("messageBean: " + messageBean);
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
    public boolean unregister(OnBottomMessageListener onBottomMessageListener) {
        if (onBottomMessageListener != null) {
            long timeTag = 0;
            Iterator<Map.Entry<Long, OnBottomMessageListener>> it = onBottomMessageListenerMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Long, OnBottomMessageListener> entry = it.next();
                if (entry.getValue() == onBottomMessageListener) {
                    timeTag = entry.getKey();
                    it.remove();
                }
            }
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
                    buyApple.unregister(timeTag);
                    return true;
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return false;
        } else {
            return false;
        }

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
                return buyApple.isStartSystemMic();
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
                return buyApple.switchMicrophoneChannel();
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
                return buyApple.restoreChannel();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 设置按键学习
     * true为打开
     * false为关闭
     *
     * @return
     */
    public boolean setLearningButtons(boolean isLearningButtons) {
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
                buyApple.setLearningButtons(isLearningButtons);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 当前与原车是否连接成功
     *
     * @return
     */
    public boolean isConnectOriginalVehicleSuccess() {
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
                return buyApple.isConnectOriginalVehicleSuccess();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 当前与原车是否连接成功
     *
     * @return
     */
    public byte[] getCarModel() {
        if (null == context) {
            Log.d(TAG, "BottomMessengerUtil Not init Context Is Null");
            return null;
        }
        RemoteTransfer.getInstance().setCurrentAuthority(DispatcherConstants.AUTHORITY_BOTTOM_MESSAGE);
        IBinder iBottomMessenger = Andromeda.with(context).getRemoteService(IBottomMessenger.class);
        if (null == iBottomMessenger) {
            Log.d(TAG, "iBottomMessenger is Null");
            return null;
        }
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                return buyApple.getCarModel();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
