package com.example.jezuz1n.hairly.models.dto;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public class CitaDTO {

    public static final int TYPE_ACTIVE = 1;
    public static final int TYPE_NOT_ACTIVE = 2;
    public static final int TYPE_CANCELED = 3;

    private int day, month, year, hour, minute;
    private String UIDclient, UIDshop;
    private int state;

    public CitaDTO() {
    }

    public CitaDTO(int day, int month, int year, int hour, int minute, String UIDclient, String UIDshop, int state) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.UIDclient = UIDclient;
        this.UIDshop = UIDshop;
        this.state = state;
    }

    public CitaDTO(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public String getUIDclient() {
        return UIDclient;
    }

    public void setUIDclient(String UIDclient) {
        this.UIDclient = UIDclient;
    }

    public String getUIDshop() {
        return UIDshop;
    }

    public void setUIDshop(String UIDshop) {
        this.UIDshop = UIDshop;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
