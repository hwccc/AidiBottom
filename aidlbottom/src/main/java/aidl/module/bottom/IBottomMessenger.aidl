// IBottomMessenger.aidl
package aidl.module.bottom;
import aidl.module.bottom.BottomMessage;
import org.qiyi.video.svg.IPCCallback;

interface IBottomMessenger {

    int register(long timeTag, IPCCallback callback);

    int unregister(long timeTag);

    int send(in BottomMessage bottomMessage);

    int getDidiPlayVersion();

    boolean switchMicrophoneChannel();

    boolean restoreChannel();

    boolean isStartSystemMic();

    boolean setSaveMicPort(boolean isSaveMicPort);

    boolean isConnectOriginalVehicleSuccess();

    boolean setStartSystemMic(boolean isStartSystemMic);

    byte[] getCarModel();
}
