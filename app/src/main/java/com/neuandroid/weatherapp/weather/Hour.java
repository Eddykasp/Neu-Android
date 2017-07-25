package com.neuandroid.weatherapp.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mac on 19/07/2017.
 */

public class Hour implements Parcelable{

    private long jTime;
    private String jSummary;
    private double jTemperature;
    private String jIcon;
    private String jTimezone;

    public long getTime() {
        return jTime;
    }

    public void setTime(long time) {
        jTime = time;
    }

    public String getSummary() {
        return jSummary;
    }

    public void setSummary(String summary) {
        jSummary = summary;
    }

    public int getTemperature() {

        return (int)Math.round(jTemperature);
    }

    public void setTemperature(double temperature) {

        jTemperature = temperature;
    }

    public String getIcon() {
        return jIcon;
    }

    public void setIcon(String icon) {
        jIcon = icon;
    }

    public int getIconId(){

        return Forecast.getIconIdea(jIcon);
    }

    public String getTimezone() {
        return jTimezone;
    }

    public void setTimezone(String timezone) {
        jTimezone = timezone;
    }

    public String getHour(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h a");
        Date date = new Date(jTime * 1000);

        return simpleDateFormat.format(date);
    }
    @Override
    public int describeContents() {
        return 0;//ignore
    }

    @Override
    public void writeToParcel(Parcel destination, int position) {

        destination.writeLong(jTime);
        destination.writeDouble(jTemperature);
        destination.writeString(jSummary);
        destination.writeString(jIcon);
        destination.writeString(jTimezone);

    }

    public Hour(Parcel in){
        jTime = in.readLong();
        jTemperature = in.readDouble();
        jSummary = in.readString();
        jIcon = in.readString();
        jTimezone = in.readString();
    }

    public Hour(){}

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {

        @Override
        public Hour createFromParcel(Parcel source) {
            return new Hour(source);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };
}
