package com.example.jezuz1n.hairly.models.dto;

import android.net.Uri;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public class ClientDTO extends UserDTO {

    private String nick;
    private String phone;
    private Uri photoURL;


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Uri getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(Uri photoURL) {
        this.photoURL = photoURL;
    }
}
