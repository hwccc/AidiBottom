package com.hwc.aidlbottom.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class BottomServiceUtil {

    private static final String TAG = BottomServiceUtil.class.getSimpleName();

    private final String VOICE_PACKAGE_NAME = "com.miudrive.syscom";

    private volatile static BottomServiceUtil instance;

    public static BottomServiceUtil getInstance() {
        if (instance == null) {
            synchronized (BottomServiceUtil.class) {
                if (instance == null) {
                    instance = new BottomServiceUtil();
                }
            }
        }
        return instance;
    }

    private BottomServiceUtil() {

    }


    /**
     * 启动服务
     *
     * @return
     */
    public void startService(Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_INSERT);
        intent.setData(Uri.parse("miudrive_syscom://service"));
        intent.setPackage(VOICE_PACKAGE_NAME);
        context.startService(intent);
    }

}
