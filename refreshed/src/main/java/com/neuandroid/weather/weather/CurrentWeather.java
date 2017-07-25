package com.neuandroid.weather.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mac on 18/07/2017.
 */

public class CurrentWeather {

    private String jIcon;
    private long jTime;
    private double jTemperature;
    private double jHumidity;
    private double jPrecipChance;
    private String jSummary;
    private String jTimezone;

    public String getTimezone() {
        return jTimezone;
    }

    public void setTimezone(String timezone) {
        jTimezone = timezone;
    }

    public String getIcon() {
        return jIcon;
    }

    public void setIcon(String icon) {
        jIcon = icon;
    }

    public int getIconId() {

        return Forecast.getIconIdea(jIcon);
    }

    public long getTime() {
        return jTime;
    }

    public void setTime(long time) {
        jTime = time;
    }

    public String getLastRefresh(){

        SimpleDateFormat format = new SimpleDateFormat("dd/MM h:mm:ss a");
        format.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        Date date = new Date(getTime() * 1000);
        String time = format.format(date);

        return time;
    }
    public String getFormatTime(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd  h:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone(getTimezone()));
        Date date = new Date(getTime() * 1000);
        String timeString = dateFormat.format(date);

        return timeString;
    }
    public int getTemperature() {

        return (int)Math.round(jTemperature);
    }

    public void setTemperature(double temperature) {
        jTemperature = temperature;
    }

    public int getHumidity() {

        double humidity = jHumidity * 100;

        return (int)Math.round(humidity);
    }

    public void setHumidity(double humidity) {
        jHumidity = humidity;
    }

    public int getPrecipChance() {

        double precipChance = jPrecipChance * 100;

        return (int)Math.round(precipChance);
    }

    public void setPrecipChance(double precipChance) {
        jPrecipChance = precipChance;
    }

    public String getSummary() {
        return jSummary;
    }

    public void setSummary(String summary) {
        jSummary = summary;
    }

}
