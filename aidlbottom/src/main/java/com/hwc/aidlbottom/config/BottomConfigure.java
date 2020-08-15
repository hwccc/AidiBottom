package com.hwc.aidlbottom.config;

public interface BottomConfigure {

    /**
     * 按键类型
     */
    String TYPE_BUTTONS = "type_buttons";

    /**
     * 按键类型
     */
    String TYPE_BUTTONS_LEARNING = "type_buttons_learning";

    /**
     * 停止麦克风类型
     */
    String TYPE_STOP_MIC = "type_stop_mic";

    /**
     * 执行adb命令类型
     */
    String TYPE_EXECUTE_ADB_COMMAND = "type_execute_adb_command";

    /**
     * 按上键
     */
    int FOCUS_KEYCODE_DPAD_UP = 3;

    /**
     * 按下键
     */
    int FOCUS_KEYCODE_DPAD_DOWN = 4;

    /**
     * 按左键
     */
    int FOCUS_KEYCODE_DPAD_LEFT = 1;

    /**
     * 按右键
     */
    int FOCUS_KEYCODE_DPAD_RIGHT = 2;


    /**
     * 向左边旋转
     */
    int FOCUS_KEYCODE_ROTATE_LEFT = 7;

    /**
     * 向右边旋转
     */
    int FOCUS_KEYCODE_ROTATE_RIGHT = 8;

    /**
     * 向上边滑动
     */
    int FOCUS_KEYCODE_SLIDE_UP = 9;

    /**
     * 向下边滑动
     */
    int FOCUS_KEYCODE_SLIDE_DOWN = 10;

    /**
     * 向左边滑动
     */
    int FOCUS_KEYCODE_SLIDE_LEFT = 11;

    /**
     * 向右边滑动
     */
    int FOCUS_KEYCODE_SLIDE_RIGHT = 12;

    /**
     * 返回键消息
     */
    int FOCUS_KEYCODE_BACK = 13;


    /**
     * 按下消息
     */
    int FOCUS_KEYCODE_DOWN = 14;

    /**
     * 抬起消息
     */
    int FOCUS_KEYCODE_UP = 15;

    /**
     * 按确认键
     */
    int FOCUS_KEYCODE_ENTER = 5;

    /**
     * 长按
     */
    int FOCUS_KEYCODE_LONG_PRESS = 6;

    /**
     * 物理返回键
     */
    int FOCUS_KEY_BACK = 101;

    /**
     * 上一曲
     */
    String KEYCODE_MEDIA_PREVIOUS = "com.didi365.miudrive.media.pre";

    /**
     * 下一曲
     */
    String KEYCODE_MEDIA_NEXT = "com.didi365.miudrive.media.next";

    /**
     * 暂停
     */
    String KEYCODE_MEDIA_PAUSE = "com.didi365.miudrive.media.pause";

    /**
     * 播放
     */
    String KEYCODE_MEDIA_PLAY = "com.didi365.miudrive.media.play";

    /**
     * 暂停、播放
     */
    String KEYCODE_MEDIA_PLAY_PAUSE = "com.didi365.miudrive.media.play_pause";

    /**
     * 系统按上键命令
     */
    String CMD_SYSTEM_KEYCODE_DPAD_UP = "input keyevent 19";

    /**
     * 系统按下键命令
     */
    String CMD_SYSTEM_KEYCODE_DPAD_DOWN = "input keyevent 20";

    /**
     * 系统按左键命令
     */
    String CMD_SYSTEM_KEYCODE_DPAD_LEFT = "input keyevent 21";

    /**
     * 系统按右键命令
     */
    String CMD_SYSTEM_KEYCODE_DPAD_RIGHT = "input keyevent 22";

    /**
     * 系统按确定命令
     */
    String CMD_SYSTEM_KEYCODE_ENTER = "input keyevent 66";


    /********************* 喵驾盒子底层发起协议 *********************/

    /******************************** TODO didiplay-->apk ******************************/

    /**
     * 按键未知消息学习、匹配
     */
    byte MIUDRIVE_BUTTONS_LEARNING = (byte) 0x00;

    /**
     * 返回键消息
     */
    byte MJ_BOX_FOCUS_KEYCODE_BACK_KEY = (byte) 0x01;

    /**
     * 按上键
     */
    byte MJ_BOX_FOCUS_KEYCODE_DPAD_UP = 0x02;

    /**
     * 按下键
     */
    byte MJ_BOX_FOCUS_KEYCODE_DPAD_DOWN = 0x03;

    /**
     * 按左键
     */
    byte MJ_BOX_FOCUS_KEYCODE_DPAD_LEFT = 0x04;

    /**
     * 按右键
     */
    byte MJ_BOX_FOCUS_KEYCODE_DPAD_RIGHT = 0x05;

    /**
     * 按确认键
     */
    byte MJ_BOX_FOCUS_KEYCODE_ENTER = 0x06;

    /**
     * 向右边旋转
     */
    byte MJ_BOX_FOCUS_KEYCODE_ROTATE_RIGHT = 0x07;

    /**
     * 向左边旋转
     */
    byte MJ_BOX_FOCUS_KEYCODE_ROTATE_LEFT = 0x08;

    /**
     * 上一曲
     */
    byte MJ_BOX_FOCUS_KEYCODE_MEDIA_PREVIOUS = 0x09;

    /**
     * 下一曲
     */
    byte MJ_BOX_FOCUS_KEYCODE_MEDIA_NEXT = 0x0A;

    /**
     * 暂停、播放
     */
    byte MJ_BOX_FOCUS_KEYCODE_MEDIA_PLAY_PAUSE = 0x0B;

    /**
     * 长按
     */
    byte MJ_BOX_FOCUS_KEYCODE_LONG_PRESS = 0x0C;

    /**
     * 向右边旋转多格
     */
    byte MJ_BOX_FOCUS_KEYCODE_ROTATE_MULTI_LATTICE_RIGHT = 0x0D;

    /**
     * 向左边旋转多格
     */
    byte MJ_BOX_FOCUS_KEYCODE_ROTATE_MULTI_LATTICE_LEFT = 0x0E;

    /**
     * 打开或关闭Siri
     */
    byte MJ_BOX_FOCUS_KEYCODE_OPEN_CLOSE_SIRI = 0x0F;

    /**
     * 播放
     */
    byte MJ_BOX_FOCUS_KEYCODE_MEDIA_PLAY = 0x10;

    /**
     * 暂停
     */
    byte MJ_BOX_FOCUS_KEYCODE_MEDIA_PAUSE = 0x11;

    /**
     * 关闭Siri
     */
    byte MJ_BOX_FOCUS_KEYCODE_CLOSE_SIRI = 0x12;

    /**
     * MIC切换成功
     */
    byte MJ_BOX_FOCUS_KEYCODE_SWITCH_MIC_SUCESS = (byte) 0x13;

    /**
     * 向上边滑动
     */
    byte MJ_BOX_FOCUS_KEYCODE_SLIDE_UP = 0x14;

    /**
     * 向下边滑动
     */
    byte MJ_BOX_FOCUS_KEYCODE_SLIDE_DOWN = 0x15;


    /**
     * 向左边滑动
     */
    byte MJ_BOX_FOCUS_KEYCODE_SLIDE_LEFT = 0x16;

    /**
     * 向右边滑动
     */
    byte MJ_BOX_FOCUS_KEYCODE_SLIDE_RIGHT = 0x17;


    /**
     * 屏幕焦点在喵驾
     */
    byte INTERFACE_FOCUS_TO_MIUDRIVE = 0x18;

    /**
     * 屏幕焦点在原车
     */
    byte INTERFACE_FOCUS_TO_VEHICLE = 0x19;

    /**
     * 返回喵驾主页
     */
    byte BACK_MIUDRIVE_HOME = 0x20;

    /**
     * 关屏
     */
    byte BACK_MIUDRIVE_SCREEN_OFF = 0x21;

    /**
     * 不能返回原车
     */
    byte BACK_MIUDRIVE_NO_RETURN_TO_ORIGINAL_VEHICLE = 0x22;

    /**
     * 接听电话
     */
    byte MJ_BOX_FOCUS_KEYCODE_ANSWER_THE_PHONE = (byte) 0x23;

    /**
     * 挂电话
     */
    byte MJ_BOX_FOCUS_KEYCODE_CLOSE_THE_PHONE = (byte) 0x24;

    /**
     * 返回键消息
     */
    byte MJ_BOX_FOCUS_KEYCODE_RETURN_KEY = (byte) 0x25;

    /**
     * 底层异常重启消息
     */
    byte MJ_BOX_BOTTOM_ABNORMAL_RESTART = (byte) 0x26;

    /**
     * 连接原车成功
     */
    byte MJ_BOX_CONNECT_ORIGINAL_VEHICLE_SUCCESS = (byte) 0x27;

    /**
     * 按下事件
     */
    byte FOCUS_KEYCODE_PRESS_DOWN = (byte) 0x28;

    /**
     * 抬起事件
     */
    byte FOCUS_KEYCODE_PRESS_UP = (byte) 0x29;

    /**
     * 屏幕按下事件
     */
    byte FOCUS_KEYCODE_SCREEN_PRESS_DOWN = (byte) 0x30;

    /**
     * 屏幕抬起事件
     */
    byte FOCUS_KEYCODE_SCREEN_PRESS_UP = (byte) 0x31;

    /**
     * 获取didiplay版本号
     */
    byte MJ_BOX_GET_DIDIPLAY_VERSION = (byte) 0x32;

    /**
     * 车机型号
     */
    byte MJ_BOX_FOCUS_KEYCODE_CAR_MODE = (byte) 0xFD;


    /******************************** TODO apk-->didiplay******************************/

    /**
     * 返回原车
     */
    byte MJ_BOX_KEYCODE_RETURN_ORIGINAL_CAR = (byte) 0xFE;

    /**
     * 进入地图
     */
    byte MJ_BOX_FOCUS_KEYCODE_GET_INTO_MAP = (byte) 0xFC;

    /**
     * 退出地图
     */
    byte MJ_BOX_FOCUS_KEYCODE_SIGN_OUT_MAP = (byte) 0xFB;


    /**
     * 开始语音识别
     */
    byte MJ_BOX_FOCUS_KEYCODE_START_SPEECH = (byte) 0xFA;

    /**
     * 结束语音识别
     */
    byte MJ_BOX_FOCUS_KEYCODE_STOP_SPEECH = (byte) 0xF9;

    /**
     * 播放事件
     */
    byte MJ_BOX_FOCUS_KEYCODE_PLAY_ACTION = (byte) 0xF8;

    /**
     * 导航播报开始(进行混音操作)
     */
    byte MJ_BOX_FOCUS_KEYCODE_NAVI_START_TTS = (byte) 0xF7;

    /**
     * 导航播报结束（关闭混音操作）
     */
    byte MJ_BOX_FOCUS_KEYCODE_NAVI_STOP_TTS = (byte) 0xF6;

    /**
     * 接听来电响铃
     */
    byte MJ_BOX_KEYCODE_ANSWER_INCOMING_RING = (byte) 0xF5;

    /**
     * 接听来电通话
     */
    byte MJ_BOX_KEYCODE_ANSWER_INCOMING_CALL = (byte) 0xF4;

    /**
     * 挂断
     */
    byte MJ_BOX_KEYCODE_HANG_UP = (byte) 0xF3;

    /**
     * 拨打电话
     */
    byte MJ_BOX_KEYCODE_CALL_UP = (byte) 0xF2;

    /**
     * 喵驾发送至底层暂停
     */
    byte MIUDRIVE_TO_VEHICLE_PAUSE = (byte) 0xF1;

    /**
     * 模拟触摸
     */
    byte MIUDRIVE_ANALOG_TOUCH= (byte) 0xF0;

    /**
     * 关闭键盘下发消息
     */
    byte MJ_BOX_CLOSE_KEYBOARD_ISSUED_NEWS = (byte) 0xEF;

    /**
     * 开启键盘下发消息
     */
    byte MJ_BOX_OPEN_KEYBOARD_ISSUED_NEWS = (byte) 0xEE;

    /**
     * 模拟按键
     */
    byte MJ_BOX_SIMULATE_KEYSTROKES = (byte) 0xED;

    /**
     * 开启鼠标下发事件
     */
    byte MJ_BOX_OPEN_MOUSE_ISSUED_NEWS = (byte) 0xEC;

    /**
     * 关闭鼠标下发事件
     */
    byte MJ_BOX_CLOSE_MOUSE_ISSUED_NEWS = (byte) 0xEB;

    /**
     * 打开返回键拦截事件
     */
    byte MJ_BOX_OPEN_BACK_INTERCEPT = (byte) 0xEA;

    /**
     * 关闭返回键拦截事件
     */
    byte MJ_BOX_CLOSE_BACK_INTERCEPT = (byte) 0xE9;

    /**
     * 电话名称
     */
    byte MJ_BOX_KEYCODE_CALLPHONE_NAME = (byte) 0xE8;

    /**
     * 执行adb命令
     */
    byte MJ_BOX_EXECUTE_ADB_COMMAND = (byte) 0xE7;






    /**********************TODO 发送给底层消息时，携带的数据参数***************************/
    /**
     * 立即开始播放更新
     */
    byte MJ_BOX_KEYCODE_START_NOW_PLAYING_UPDATES = (byte) 0x00;

    /**
     * 立即停止播放更新
     */
    byte MJ_BOX_KEYCODE_STOP_NOW_PLAYING_UPDATES = (byte) 0x02;

    /**
     * 语音识别结束声音和屏幕焦点恢复以前状态
     */
    byte MIUDRIVE_NO_HANDLE_SOUND_FOCUS = (byte) 0x00;

    /**
     * 语音识别结束声音焦点在喵驾，屏幕恢复以前状态
     */
    byte MIUDRIVE_NEED_HANDLE_SOUND_FOCUS = (byte) 0x01;

    /**
     * 语音识别结束屏幕焦点在喵驾，声音恢复以前状态
     */
    byte INTERFACE_SWITCH_MIUDRIVE = (byte) 0x02;

}
