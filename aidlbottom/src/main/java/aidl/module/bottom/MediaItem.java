package aidl.module.bottom;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaItem implements Parcelable {

    /**
     * 标识符
     */
    public int identifier;

    /**
     * 标题
     */
    public String title;

    /**
     * 播放时长（毫秒）
     */
    public long playTime;

    /**
     * 专辑名称
     */
    public String albumTitle;

    /**
     * 艺术家
     */
    public String artist;

    /**
     * 歌词
     */
    public String lyric;

    /**
     * 播放状态
     */
    public int playStatus = -1;

    /**
     * 播放时长（毫秒）
     */
    public long elapsedTime;


    public MediaItem() {

    }

    protected MediaItem(Parcel in) {
        identifier = in.readInt();
        title = in.readString();
        playTime = in.readLong();
        albumTitle = in.readString();
        artist = in.readString();
        lyric = in.readString();
        playStatus = in.readInt();
        elapsedTime = in.readLong();
    }

    public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
        @Override
        public MediaItem createFromParcel(Parcel in) {
            return new MediaItem(in);
        }

        @Override
        public MediaItem[] newArray(int size) {
            return new MediaItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(identifier);
        parcel.writeString(title);
        parcel.writeLong(playTime);
        parcel.writeString(albumTitle);
        parcel.writeString(artist);
        parcel.writeString(lyric);
        parcel.writeInt(playStatus);
        parcel.writeLong(elapsedTime);
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "identifier=" + identifier +
                ", title='" + title + '\'' +
                ", playTime=" + playTime +
                ", albumTitle='" + albumTitle + '\'' +
                ", artist='" + artist + '\'' +
                ", lyric='" + lyric + '\'' +
                ", playStatus=" + playStatus +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
