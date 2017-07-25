package com.neuandroid.weatherapp.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.neuandroid.weatherapp.R;
import com.neuandroid.weatherapp.adapters.DayAdapter;
import com.neuandroid.weatherapp.weather.Day;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyForecastActivity extends ListActivity {

    private Day[] jDays;
    @BindView(R.id.nodaily_forecast) TextView jTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);

//        String[] daysOfTheWeek = { "Sunday", "Monday", "Tuesday",
//                "Wednesday", "Thursday", "Friday", "Saturday" };
        jTextView.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        jDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        DayAdapter adapter = new DayAdapter(this, jDays);
        setListAdapter(adapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        v.offsetLeftAndRight(30);
        String dayOfTheWeek = jDays[position].getDayOfWeek();
        String conditions = jDays[position].getSummary();
        String highTemp = jDays[position].getTemperatureMax() + "";
        String message = String.format("On %s the high will be %s and it will be %s",dayOfTheWeek,highTemp,conditions);

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

}
