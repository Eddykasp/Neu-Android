package com.neuandroid.weatherapp.ui;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by mac on 19/07/2017.
 */

public class ColorWheel {

    String [] colors = {

            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#f9845b", // orange
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#b7c0c7", // light gray
            "#800000", // Maroon
            "#808000", // Olive
            "#FF6347", // tomato
            "#FFA07A", // light salmon
            "#CD853F", // peru

    };

    public int getColor(){

        String color;
        Random random = new Random();

        int randoNumber = random.nextInt(colors.length);

        color = colors[randoNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

}

