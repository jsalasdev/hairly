package com.example.jezuz1n.hairly.models.dto;

import android.net.Uri;

import java.sql.Timestamp;

/**
 * Created by jezuz1n on 24/05/2017.
 */

public class CitaDTO {

    public static final String TYPE_ACTIVE = "confirmed";
    public static final String TYPE_NOT_ACTIVE = "running";
    public static final String TYPE_CANCELED = "deleted";
    public static final String ALL_TYPES = "all";

    private long day, month, year, hour, minute;
    private String UIDclient, UIDshop;
    private String state;
    private long timeStamp;
    public boolean pinned;

    public CitaDTO() {
    }

    public CitaDTO(long day, long month, long year, long hour, long minute, String UIDclient, String UIDshop, String state) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.UIDclient = UIDclient;
        this.UIDshop = UIDshop;
        this.state = state;
    }

    public CitaDTO(long day, long month, long year, long hour, long minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public CitaDTO(long day, long month, long year, long hour, long minute, String UIDclient, String UIDshop, String timeStamp, String state) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.UIDclient = UIDclient;
        this.UIDshop = UIDshop;
        this.state = state;
    }

    public String generateTimeStamp(){
        return String.valueOf(this.year+this.month+this.day+this.hour+this.minute);
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getDay() {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }
}
