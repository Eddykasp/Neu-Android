package com.neuandroid.weatherapp.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.neuandroid.weatherapp.R;
import com.neuandroid.weatherapp.adapters.HourAdapter;
import com.neuandroid.weatherapp.weather.Hour;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyForecastActivity extends AppCompatActivity {

    Hour[] jHours;
    @BindView(R.id.recyclerView) RecyclerView jRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        jHours = Arrays.copyOf(parcelables,parcelables.length,Hour[].class);

        HourAdapter hourAdapter = new HourAdapter(this,jHours);
        jRecyclerView.setAdapter(hourAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        jRecyclerView.setLayoutManager(layoutManager);

        jRecyclerView.setHasFixedSize(true);


    }
}
