package com.example.jezuz1n.hairly.models.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jesus.salas on 31/05/2017.
 */

public class ShopMapVO implements Parcelable {
    private String uid, latitude, longitude, nick;

    protected ShopMapVO(Parcel in) {
        uid = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        nick = in.readString();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public static final Creator<ShopMapVO> CREATOR = new Creator<ShopMapVO>() {
        @Override
        public ShopMapVO createFromParcel(Parcel in) {
            return new ShopMapVO(in);
        }

        @Override
        public ShopMapVO[] newArray(int size) {
            return new ShopMapVO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(nick);
    }
}
