// IBottomMessenger.aidl
package aidl.module.bottom;
import aidl.module.bottom.BottomMessage;
import org.qiyi.video.svg.IPCCallback;

interface IBottomMessenger {

    int register(IPCCallback callback);

    int unregister();

    int send(in BottomMessage bottomMessage);

    int getDidiPlayVersion();

    int switchMicrophoneChannel();

    int restoreChannel();

    boolean isStartSystemMic();
}
