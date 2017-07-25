package com.neuandroid.weatherapp.weather;

import com.neuandroid.weatherapp.R;

/**
 * Created by mac on 19/07/2017.
 */

public class Forecast {

    private CurrentWeather jCurrent;
    private Hour[] jMHourlyForecast;
    private Day[] jMDailyForecast;

    public CurrentWeather getCurrent() {
        return jCurrent;
    }

    public void setCurrent(CurrentWeather current) {
        jCurrent = current;
    }

    public Hour[] getHourlyForecast() {
        return jMHourlyForecast;
    }

    public void setHourlyForecast(Hour[] hourlyForecast) {
        jMHourlyForecast = hourlyForecast;
    }

    public Day[] getDailyForecast() {
        return jMDailyForecast;
    }

    public void setDailyForecast(Day[] dailyForecast) {
        jMDailyForecast = dailyForecast;
    }

    public static int getIconIdea(String iconString){


        // clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night.
        int iconId = R.drawable.clear_day;

        if (iconString.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (iconString.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (iconString.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (iconString.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (iconString.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (iconString.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (iconString.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (iconString.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (iconString.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (iconString.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }

        return iconId;
    }

}
