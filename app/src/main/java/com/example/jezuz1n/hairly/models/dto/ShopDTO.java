package com.example.jezuz1n.hairly.models.dto;

/**
 * Created by jezuz1n on 15/05/2017.
 */

public class ShopDTO extends UserDTO {

    private String address, description, nick, phone, province, latitude, longitude;

    public ShopDTO(){}

    public ShopDTO(String email, String type, String address, String description, String nick, String phone, String province) {
        super(email, type);
        this.address = address;
        this.description = description;
        this.nick = nick;
        this.phone = phone;
        this.province = province;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
