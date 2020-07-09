package com.hwc.aidlbottom.config;

public interface FocusConfigure {

    /**
     * 缩放时间
     */
    long SCALE_DURATION = 300;

    /**
     * X轴缩放比例
     */
    float SCALE_RATIO_X = 1.05f;

    /**
     * Y轴缩放比例
     */
    float SCALE_RATIO_Y = 1.05f;

    /**
     * EventBus消息优先级 Fragment接收端
     */
    int EVENTBUS_PRIORITY_FRAGMENT = 0;

    /**
     * EventBus消息优先级 Activity接收端
     */
    int EVENTBUS_PRIORITY_ACTICITY = 10;

    /**
     * EventBus消息优先级 二级Fragment接收端，例如界面上有多层Fragment嵌套
     */
    int EVENTBUS_PRIORITY_TWO_FRAGMENT = 20;

    /**
     * EventBus消息优先级 全屏Fragment接收端
     */
    int EVENTBUS_PRIORITY_FULL_FRAGMENT = 30;

    /**
     * EventBus消息优先级 特殊全屏Fragment接收端
     */
    int EVENTBUS_PRIORITY_FULL_SPECIAL_FRAGMENT = 31;

    /**
     * EventBus消息优先级 window接收端
     */
    int EVENTBUS_PRIORITY_WINDOW = 40;

    /**
     * EventBus消息优先级 popwindow接收端
     */
    int EVENTBUS_PRIORITY_POPWINDOW = 50;

    /**
     * EventBus消息优先级 蓝牙接收电话全局弹窗
     */
    int EVENTBUS_PRIORITY_BT_CALL_STATU = 60;

    /**
     * EventBus消息优先级 语音Dialog接收端
     */
    int EVENTBUS_PRIORITY_VOICE_DIALOG = 70;

    /**
     * EventBus消息优先级 Dialog接收端
     */
    int EVENTBUS_PRIORITY_DIALOG = 80;


    /**
     * EventBus消息优先级 Dialog,需要覆盖在其他弹窗上的dialog使用,提高自身优先级
     */
    int EVENTBUS_PRIORITY_DIALOG_2 = 90;


    /**
     * 最高级弹窗，最高优先级，用于操作弹窗拦截
     */
    int EVENTBUS_PRIORITY_HIGHEST_DIALOG = 980;

    /**
     * 悬浮窗旋钮消息
     */
    int EVENTBUS_PRIORITY_FLOAT_WINDOW = 990;

    int EVENTBUS_PRIORITY_SLIDE_WINDOW = 991;

    int EVENTBUS_PRIORITY_BT_WINDOW = 992;

    /**
     * 行车规制弹窗
     */
    int EVENTBUS_PRIORITY_TRAFFIC_WINDOW=993;

    /**
     * 录音视图启动时，最高优先级
     */
    int EVENTBUS_PRIORITY_RECOGVIEW = 1000;


    /**
     * 盘控Action
     */
    String THIRD_PARTY_ACTION = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT";

    /**
     * 盘控Extra
     */
    String THIRD_PARTY_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_THIRD_PARTY_DATA";


    /**
     * 喵驾盘控焦点移动Action
     */
    String MJ_ACTION = "com.didi365.miudrive.navi.action.KNOB_FOCUS_MOVEMENT";

    /**
     * 喵驾盘控焦点移动携带的Extra数据
     */
    String MJ_EXTRA = "com.didi365.miudrive.navi.action.KNOB_FOCUS_MOVEMENT_CARRYING_EXTRA_DATA";

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
     * 向左边旋转
     */
    byte MJ_BOX_FOCUS_KEYCODE_ROTATE_LEFT = 0x08;

    /**
     * 向右边旋转
     */
    byte MJ_BOX_FOCUS_KEYCODE_ROTATE_RIGHT = 0x07;

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
     * 长按
     */
    byte MJ_BOX_FOCUS_KEYCODE_LONG_PRESS = 0x0C;

    /**
     * 向左边旋转多格
     */
    byte MJ_BOX_FOCUS_KEYCODE_ROTATE_MULTI_LATTICE_LEFT = 0x0E;

    /**
     * 向右边旋转多格
     */
    byte MJ_BOX_FOCUS_KEYCODE_ROTATE_MULTI_LATTICE_RIGHT = 0x0D;

    /**
     * 打开或关闭Siri
     */
    byte MJ_BOX_FOCUS_KEYCODE_OPEN_CLOSE_SIRI = 0x0F;

    /**
     * 关闭Siri
     */
    byte MJ_BOX_FOCUS_KEYCODE_CLOSE_SIRI = 0x12;

    /**
     * 播放
     */
    byte MJ_BOX_FOCUS_KEYCODE_MEDIA_PLAY = 0x10;

    /**
     * 暂停
     */
    byte MJ_BOX_FOCUS_KEYCODE_MEDIA_PAUSE = 0x11;

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
     * 车机型号
     */
    byte MJ_BOX_FOCUS_KEYCODE_CAR_MODE = (byte) 0xFD;

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
     * MIC切换成功
     */
    byte MJ_BOX_FOCUS_KEYCODE_SWITCH_MIC_SUCESS = (byte) 0x13;

    /**
     * 导航播报开始(进行混音操作)
     */
    byte MJ_BOX_FOCUS_KEYCODE_NAVI_START_TTS = (byte) 0xF7;

    /**
     * 导航播报结束（关闭混音操作）
     */
    byte MJ_BOX_FOCUS_KEYCODE_NAVI_STOP_TTS = (byte) 0xF6;

    /**
     * 返回原车
     */
    byte MJ_BOX_KEYCODE_RETURN_ORIGINAL_CAR = (byte) 0xFE;

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
    //byte MJ_BOX_KEYCODE_CALL_UP = (byte) 0xF2;


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
     * 喵驾发送至底层暂停
     */
    byte MIUDRIVE_TO_VEHICLE_PAUSE = (byte) 0xF1;

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
     * 关闭键盘下发消息
     */
    byte MJ_BOX_CLOSE_KEYBOARD_ISSUED_NEWS = (byte) 0xEF;

    /**
     * 开启键盘下发消息
     */
    byte MJ_BOX_OPEN_KEYBOARD_ISSUED_NEWS = (byte) 0xEE;

    /**
     * 关闭鼠标下发事件
     */
    byte MJ_BOX_CLOSE_MOUSE_ISSUED_NEWS = (byte) 0xEB;

    /**
     * 开启鼠标下发事件
     */
    byte MJ_BOX_OPEN_MOUSE_ISSUED_NEWS = (byte) 0xEC;


    /**
     * 模拟按键
     */
    byte MJ_BOX_SIMULATE_KEYSTROKES = (byte) 0xED;


    /**
     * 关闭返回键拦截事件
     */
    byte MJ_BOX_CLOSE_BACK_INTERCEPT = (byte) 0xE9;

    /**
     * 打开返回键拦截事件
     */
    byte MJ_BOX_OPEN_BACK_INTERCEPT = (byte) 0xEA;

}
