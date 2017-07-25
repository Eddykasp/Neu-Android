package com.neuandroid.weatherapp.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mac on 19/07/2017.
 */

public class Day implements Parcelable{

    private long jTime;
    private String jMSummary;
    private double jMTemperatureMax;
    private String jMIcon;
    private String jMTimezone;
    private Parcel jIn;

    public Day(Parcel in) {
        jTime = in.readLong();
        jMSummary = in.readString();
        jMTemperatureMax = in.readDouble();
        jMIcon = in.readString();
        jMTimezone = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public Day() {

    }

    public long getTime() {
        return jTime;
    }

    public void setTime(long time) {
        jTime = time;
    }

    public String getSummary() {
        return jMSummary;
    }

    public void setSummary(String summary) {
        jMSummary = summary;
    }

    public int getTemperatureMax() {

        return (int)Math.round(jMTemperatureMax);
    }

    public void setTemperatureMax(double temperatureMax) {

        jMTemperatureMax = temperatureMax;
    }

    public String getIcon() {
        return jMIcon;
    }

    public void setIcon(String icon) {
        jMIcon = icon;
    }

    public String getTimezone() {
        return jMTimezone;
    }

    public void setTimezone(String timezone) {
        jMTimezone = timezone;
    }

    public int getIconId(){

        return Forecast.getIconIdea(jMIcon);
    }

    public String getDayOfWeek(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(jMTimezone));

        Date date = new Date(jTime * 1000);

        return simpleDateFormat.format(date);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destinatio, int flag) {

        destinatio.writeLong(jTime);
        destinatio.writeString(jMSummary);
        destinatio.writeDouble(jMTemperatureMax);
        destinatio.writeString(jMIcon);
        destinatio.writeString(jMTimezone);
    }

//    public static  final Creator<Day> CREATOR = new Creator<Day>(){
//
//        @Override
//        public Day createFromParcel(Parcel source){
//
//            return new Day(source);
//        }
//
//        @Override
//        public Day[] newArray(int size){
//
//            return new Day[size];
//        }
//    };

}
