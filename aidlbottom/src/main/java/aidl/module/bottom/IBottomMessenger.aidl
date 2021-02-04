// IBottomMessenger.aidl
package aidl.module.bottom;
import aidl.module.bottom.BottomMessage;
import aidl.module.bottom.MediaItem;
import org.qiyi.video.svg.IPCCallback;

interface IBottomMessenger {

    int register(long timeTag, IPCCallback callback);

    int unregister(long timeTag);

    int registerMicStatus(long timeTag, IPCCallback callback);

    int unregisterMicStatus(long timeTag);

    int send(in BottomMessage bottomMessage);

    int getDidiPlayVersion();

    boolean switchMicrophoneChannel();

    boolean restoreChannel();

    boolean isStartSystemMic();

    boolean setLearningButtons(boolean isLearningButtons);

    boolean setSaveMicPort(boolean isSaveMicPort);

    boolean isConnectOriginalVehicleSuccess();

    boolean isCarSpecial();

    boolean isPrivateCarSpecialInterface();

    byte[] getCarModel();

    int getAppCarModel();

    String getAppSystemSource();

    String getMessage();

    boolean setMessage(in String message);

    int registerMessage(long timeTag, IPCCallback callback);

    int unregisterMessage(long timeTag);

    int sendMedia(in MediaItem mediaItem);
}
