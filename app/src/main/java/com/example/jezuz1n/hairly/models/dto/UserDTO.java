package com.example.jezuz1n.hairly.models.dto;

/**
 * Created by jezuz1n on 24/04/2017.
 */

public class UserDTO {

    private String name, email, address,uid, type, password;

    public UserDTO(){}

    public UserDTO(String name, String email, String address, String type){
        this.name = name;
        this.email = email;
        this.address = address;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
