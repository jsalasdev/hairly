package com.example.jezuz1n.hairly.models.dto;

/**
 * Created by jezuz1n on 24/04/2017.
 */

public class UserDTO {

    private String email,uid, type, password;
    boolean firstConnection;

    public UserDTO(){}

    public UserDTO(String email, String type){
        this.email = email;
        this.type = type;
    }

    public boolean isFirstConnection() {
        return firstConnection;
    }

    public void setFirstConnection(boolean firstConnection) {
        this.firstConnection = firstConnection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
