package com.example.jezuz1n.hairly.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.jezuz1n.hairly.models.dto.UserDTO;

import java.util.HashMap;

/**
 * Created by jezuz1n on 23/04/2017.
 */

public class SessionManager {

    SharedPreferences.Editor editor;
    Context context;
    SharedPreferences pref;

    public static final String IS_LOGGED = "IsLogged";
    public static final String NAME = "userPref";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_UID = "uid";
    public static final String KEY_TYPE  = "type";

    public SessionManager(Context context){
        this.context = context;
        init();
    }

    public void init(){
        pref = context.getSharedPreferences(NAME,context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createSession(UserDTO user){
        editor.putBoolean(IS_LOGGED,true);
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_UID, user.getUid());
        editor.putString(KEY_TYPE, user.getType());
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_UID,pref.getString(KEY_UID,null));
        user.put(KEY_TYPE,pref.getString(KEY_TYPE,null));

        return user;
    }

    public boolean isLogged(){
        return pref.getBoolean(IS_LOGGED,false);
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }

}
