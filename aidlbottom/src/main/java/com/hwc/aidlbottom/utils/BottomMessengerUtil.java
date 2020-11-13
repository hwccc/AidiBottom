package com.hwc.aidlbottom.utils;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.hwc.aidlbottom.bean.MessageBean;
import com.hwc.aidlbottom.config.BottomConfigure;
import com.hwc.aidlbottom.listener.OnBottomMessageListener;
import com.hwc.aidlbottom.listener.OnBottomMicListener;

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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import aidl.module.bottom.BottomMessage;
import aidl.module.bottom.IBottomMessenger;

/**
 * @author hwc
 */
public class BottomMessengerUtil extends BaseProcessUtil {

    private Map<Long, OnBottomMessageListener> onBottomMessageListenerMap;

    private Map<Long, OnBottomMicListener> onBottomMicListenerMap;

    /**
     * 异常是否重新发送数据
     */
    private boolean isErrorResend;

    private BlockingQueue<BottomMessage> linkedBlockingQueue;

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
        onBottomMicListenerMap = new HashMap<>();
        linkedBlockingQueue = new LinkedBlockingQueue<>();
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

                    if (onBottomMicListenerMap != null) {
                        Iterator<Map.Entry<Long, OnBottomMicListener>> it = onBottomMicListenerMap.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<Long, OnBottomMicListener> entry = it.next();
                            registerMicStatus(entry.getKey(), entry.getValue());
                        }
                    }
                    if (isErrorResend) {
                        if (linkedBlockingQueue != null) {
                            BottomMessage bottomMessage;
                            while ((bottomMessage = linkedBlockingQueue.poll()) != null) {
                                reSend(bottomMessage);
                            }
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
        onBottomMessageListenerMap.put(timeTag, onBottomMessageListener);
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "register");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.register(timeTag, new BaseCallback() {

                    @Override
                    public void onSucceed(Bundle result) {
                        MessageBean messageBean = (MessageBean) result.getSerializable("MessageBean");
                        if (onBottomMessageListenerMap != null && onBottomMessageListenerMap.containsValue(onBottomMessageListener)) {
                            if (messageBean != null) {
                                if (TextUtils.equals(BottomConfigure.TYPE_BUTTONS, messageBean.msgType)) {
                                    onBottomMessageListener.onButtonsMessage(messageBean.bottomBytes);
                                } else if (TextUtils.equals(BottomConfigure.TYPE_BUTTONS_LEARNING, messageBean.msgType)) {
                                    onBottomMessageListener.onButtonsLearningMessage(messageBean.bottomBytes);
                                } else {
                                    onBottomMessageListener.onMessage(messageBean.bottomBytes);
                                }
                                org.qiyi.video.svg.log.Logger.d("messageBean: " + messageBean);
                            } else {
                                int isPrivateCarSpecialInterface = result.getInt("isPrivateCarSpecialInterface", -1);
                                if (isPrivateCarSpecialInterface != -1) {
                                    org.qiyi.video.svg.log.Logger.d("isPrivateCarSpecialInterface: " + isPrivateCarSpecialInterface);
                                    onBottomMessageListener.onPrivateCarSpecial(isPrivateCarSpecialInterface == 1);
                                }
                            }


                        } else {
                            org.qiyi.video.svg.log.Logger.d("onBottomMessageListener is null");
                        }
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
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return -1;
        }
        Log.d(TAG, "getDidiPlayVersion");
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
        Log.d(TAG, "unregister");
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
            IBinder iBottomMessenger = checkIsConnect();
            if (iBottomMessenger == null) {
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
     * 注册底层底层麦克风消息
     *
     * @param onBottomMicListener
     * @return
     */
    public boolean registerMicStatus(final OnBottomMicListener onBottomMicListener) {
        return registerMicStatus(System.currentTimeMillis(), onBottomMicListener);
    }


    /**
     * 注册底层麦克风消息
     *
     * @param onBottomMicListener
     * @return
     */
    private boolean registerMicStatus(long timeTag, final OnBottomMicListener onBottomMicListener) {
        onBottomMicListenerMap.put(timeTag, onBottomMicListener);
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "register");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.registerMicStatus(timeTag, new BaseCallback() {

                    @Override
                    public void onSucceed(Bundle result) {
                        boolean isOpen = result.getBoolean("isOpen");
                        if (onBottomMicListenerMap != null && onBottomMicListenerMap.containsValue(onBottomMicListener)) {
                            onBottomMicListener.onBottomMicStatus(isOpen);
                            org.qiyi.video.svg.log.Logger.d("isOpen: " + isOpen);
                        }
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
     * 注销麦克风消息
     *
     * @param onBottomMicListener
     * @return
     */
    public boolean unregisterMicStatus(OnBottomMicListener onBottomMicListener) {
        Log.d(TAG, "unregisterMicStatus");
        if (onBottomMicListener != null) {
            long timeTag = 0;
            Iterator<Map.Entry<Long, OnBottomMicListener>> it = onBottomMicListenerMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Long, OnBottomMicListener> entry = it.next();
                if (entry.getValue() == onBottomMicListener) {
                    timeTag = entry.getKey();
                    it.remove();
                }
            }
            IBinder iBottomMessenger = checkIsConnect();
            if (iBottomMessenger == null) {
                return false;
            }
            IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
            if (null != buyApple) {
                try {
                    buyApple.unregisterMicStatus(timeTag);
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
        Log.d(TAG, "send bottomMessage: " + bottomMessage);
        if (isErrorResend) {
            linkedBlockingQueue.offer(bottomMessage);
        }
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "send");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                if (isErrorResend) {
                    bottomMessage = linkedBlockingQueue.poll();
                }
                if (bottomMessage != null) {
                    buyApple.send(bottomMessage);
                }
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                if (isErrorResend) {
                    linkedBlockingQueue.offer(bottomMessage);
                }
            }
        }
        return false;
    }

    /**
     * 重新发送消息
     *
     * @param bottomMessage
     * @return
     */
    private boolean reSend(BottomMessage bottomMessage) {
        Log.d(TAG, "send bottomMessage: " + bottomMessage);
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            if (isErrorResend) {
                linkedBlockingQueue.offer(bottomMessage);
            }
            return false;
        }
        Log.d(TAG, "send");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.send(bottomMessage);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (isErrorResend) {
            linkedBlockingQueue.offer(bottomMessage);
        }
        return false;
    }

    /**
     * 系统是否启动麦克风消息
     *
     * @return
     */
    public boolean isStartSystemMic() {
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "isStartSystemMic");
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
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "switchMicrophoneChannel");
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
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "restoreChannel");
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
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "setLearningButtons isLearningButtons：" + isLearningButtons);
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
     * 设置是否保存麦克风端口
     * true为保存
     * false不保存
     *
     * @return
     */
    public boolean setSaveMicPort(boolean isSaveMicPort) {
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "setSaveMicPort isSaveMicPort：" + isSaveMicPort);
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.setSaveMicPort(isSaveMicPort);
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
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "isConnectOriginalVehicleSuccess");
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
     * 是否为专车专用
     *
     * @return
     */
    public boolean isCarSpecial() {
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "isCarSpecial");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                return buyApple.isCarSpecial();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 是否在专车专用界面
     *
     * @return
     */
    public boolean isPrivateCarSpecialInterface() {
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "isPrivateCarSpecialInterface");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                return buyApple.isPrivateCarSpecialInterface();
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
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return null;
        }
        Log.d(TAG, "getCarModel");
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


    /**
     * 获取apk的车机兼容类型
     *
     * @return
     */
    public int getAppCarModel() {
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return -1;
        }
        Log.d(TAG, "getAppCarModel");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                return buyApple.getAppCarModel();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    /**
     * 获取apk的系统来源
     *
     * @return
     */
    public String getAppSystemSource() {
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return null;
        }
        Log.d(TAG, "getAppSystemSource");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                return buyApple.getAppSystemSource();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private IBinder checkIsConnect() {
        if (null == context) {
            Log.d(TAG, "BottomMessengerUtil Not init Context Is Null");
            return null;
        }
        RemoteTransfer.getInstance().setCurrentAuthority(DispatcherConstants.AUTHORITY_BOTTOM_MESSAGE);
        IBinder iBottomMessenger = Andromeda.with(context).getRemoteService(IBottomMessenger.class);
        if (null == iBottomMessenger) {
            Log.d(TAG, "iBottomMessenger is Null");
            BottomServiceUtil.getInstance().startService(context);
        }
        return iBottomMessenger;
    }


    /**
     * 执行adb命令
     */
    public void sendAdbCommand(String adbCommand) {
        Log.d(TAG, "sendAdbCommand: " + adbCommand);
        if (!TextUtils.isEmpty(adbCommand)) {
            BottomMessage bottomMessage = new BottomMessage();
            bottomMessage.msgType = BottomConfigure.TYPE_EXECUTE_ADB_COMMAND;
            bottomMessage.bottomBytes = adbCommand.getBytes();
            send(bottomMessage);
        }
    }

    /**
     * 发送整串的数组至底层
     *
     * @param bottomBytes
     */
    public void sendNativeSocketData(byte[] bottomBytes) {
        Log.d(TAG, "sendNativeSocketData: " + bottomBytes);
        if (bottomBytes != null) {
            BottomMessage bottomMessage = new BottomMessage();
            bottomMessage.bottomBytes = bottomBytes;
            send(bottomMessage);
        }
    }

    /**
     * 发送按键事件至底层
     */
    public void sendKeystrokesData(int keyCode) {
        Log.d(TAG, "sendKeystrokesData: " + keyCode);
        BottomMessage bottomMessage = new BottomMessage();
        bottomMessage.msgType = BottomConfigure.TYPE_BUTTONS;
        bottomMessage.bottomByte = (byte) keyCode;
        send(bottomMessage);
    }

    /**
     * 发送麦克风停止事件
     *
     * @param bottomByte
     */
    public void sendStopMicData(byte bottomByte) {
        Log.d(TAG, "sendStopMicData: " + bottomByte);
        BottomMessage bottomMessage = new BottomMessage();
        bottomMessage.msgType = BottomConfigure.TYPE_STOP_MIC;
        bottomMessage.bottomByte = bottomByte;
        send(bottomMessage);
    }

    /**
     * 发送消息标准的byte字节
     *
     * @param bottomByte
     */
    public void sendSocketData(byte bottomByte) {
        Log.d(TAG, "sendSocketData: " + bottomByte);
        BottomMessage bottomMessage = new BottomMessage();
        bottomMessage.bottomByte = bottomByte;
        send(bottomMessage);
    }

    /**
     * 获取消息
     *
     * @return
     */
    public String getMessage() {
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return null;
        }
        Log.d(TAG, "getMessage");
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                return buyApple.getMessage();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 设置消息
     *
     * @param message
     * @return
     */
    public boolean setMessage(String message) {
        IBinder iBottomMessenger = checkIsConnect();
        if (iBottomMessenger == null) {
            return false;
        }
        Log.d(TAG, "setMessage message：" + message);
        IBottomMessenger buyApple = IBottomMessenger.Stub.asInterface(iBottomMessenger);
        if (null != buyApple) {
            try {
                buyApple.setMessage(message);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setErrorResend(boolean errorResend) {
        isErrorResend = errorResend;
    }
}
