package com.neuandroid.weatherapp.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
//import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.neuandroid.weatherapp.R;
import com.neuandroid.weatherapp.weather.CurrentWeather;
import com.neuandroid.weatherapp.weather.Day;
import com.neuandroid.weatherapp.weather.Forecast;
import com.neuandroid.weatherapp.weather.Hour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//import butterknife.BindView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private final static String apiKey = "1511afa975a5d87a94d32230bba7172c";
    private final static double latitude = 37.8267;
    private final static double longitude = -122.4233;
    private final static String forecastUrl = "https://api.darksky.net/forecast/"
            + apiKey + "/" + latitude + "," + longitude;
    public static String DAILY_FORECAST = "DAILY_FORECAST";
    public static String HOURLY_FORECAST = "HOURLY_FORECAST";

    private Forecast jForecast;
    private ColorWheel jColorWheel = new ColorWheel();
    private CurrentWeather jCurrentWeather = new CurrentWeather();

    private TextView jTimeLabel;
//    @BindView(R.id.temperature) TextView jTemperaqture;
    private TextView jCity;
    private TextView jTemperature;
    private ImageView jCloudState;
    private TextView jhumidityPrct;
    private TextView jRainProb;
    private TextView jRemindcomment;
    private ImageView jRefresh;
    private ProgressBar jProgressBar;
    private View jBackgourndView;
    private TextView jLastRefreshTime;
    private Button jDaily;

//    @BindView(R.id.remindComment) TextView comment;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        animate();
        jTemperature = (TextView) findViewById(R.id.temperature);
        jCity = (TextView) findViewById(R.id.city);
        jCloudState = (ImageView) findViewById(R.id.skyState);
        jhumidityPrct = (TextView) findViewById(R.id.humidityPrct);
        jTimeLabel = (TextView) findViewById(R.id.timeLabel);
        jRainProb = (TextView) findViewById(R.id.rainProb);
        jRemindcomment = (TextView) findViewById(R.id.remindComment);
        jRefresh = (ImageView) findViewById(R.id.refreshView);
        jProgressBar = (ProgressBar) findViewById(R.id.refresshprogressBar);
        jBackgourndView = findViewById(R.id.layoutBackground);
        jLastRefreshTime = (TextView) findViewById(R.id.lastRefresh);
        jDaily = (Button) findViewById(R.id.daily);


        jProgressBar.setVisibility(View.INVISIBLE);

        jRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM h:mm:ss a");
                String time = format.format(calendar.getTime());

                Toast.makeText(MainActivity.this,"Refreshing",Toast.LENGTH_LONG).show();
                jLastRefreshTime.setText("Last refresh: " + time + "");
                getForecast();
            }
        });

        getForecast();
    }

    private void getForecast() {

        if (isNetworkAvailable()) {

            int color;
            color = jColorWheel.getColor();
//            jBackgourndView.setBackgroundColor(color);

            tooglePorgressBar();
//            animate();
            OkHttpClient client = new OkHttpClient();
            Request resquest = new Request.Builder().url(forecastUrl).build();

            Call call = client.newCall(resquest);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tooglePorgressBar();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tooglePorgressBar();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {

                            jForecast = parseForeCastDetails(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    updateDisplay();
                                }
                            });

                        } else {

                            alertUserAboutError();
                        }
                    } catch (IOException | JSONException e) {
                        Log.e(TAG, "Exception caught", e);
                    }
                }
            });
        }else {

            Toast.makeText(this,"Not availble",Toast.LENGTH_LONG).show();
        }
    }

    private void animate(){

        Animator scale = AnimatorInflater.loadAnimator(this,R.animator.scale);
        scale.setTarget(jCity);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scale);
        set.start();
    }
    private void tooglePorgressBar() {

        if(jProgressBar.getVisibility() == View.INVISIBLE)
        {
            jProgressBar.setVisibility(View.VISIBLE);
            jRefresh.setVisibility(View.INVISIBLE);
        }
        else{

            jProgressBar.setVisibility(View.INVISIBLE);
            jRefresh.setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplay() {

        CurrentWeather currentWeather = jForecast.getCurrent();
        jTemperature.setText(currentWeather.getTemperature() + "");
        jhumidityPrct.setText(currentWeather.getHumidity() + "%");
        jTimeLabel.setText(currentWeather.getFormatTime() + "");
        jRainProb.setText(currentWeather.getPrecipChance() + "%");
        jRemindcomment.setText(currentWeather.getSummary() + "");
        jCity.setText(currentWeather.getTimezone() + "");

//        Drawable drawable = ResourcesCompat.getDrawable(getResources(),jCurrentWeather.getIconId(),null);
        Drawable drawable = getResources().getDrawable(currentWeather.getIconId());
        jCloudState.setImageDrawable(drawable);
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {

        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.i(TAG,"From Json:" + timezone);

        JSONObject currently = forecast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTimezone(timezone);

        Log.d(TAG,currentWeather.getFormatTime());
        return currentWeather;
    }

    private Forecast parseForeCastDetails(String jsonData) throws JSONException {

        Forecast forecast = new Forecast();

        forecast.setCurrent(getCurrentDetails(jsonData));
        forecast.setHourlyForecast(getHourlyForecast(jsonData));
        forecast.setDailyForecast(getDailyForecast(jsonData));

        return forecast;

    }

    private Day[] getDailyForecast(String jsonData) throws JSONException {

        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");

        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray data = daily.getJSONArray("data");

        Day[] days = new Day[data.length()];

        for(int i =0;i<data.length();i++){

            JSONObject jsonDay = data.getJSONObject(i);
            Day day = new Day();

            day.setSummary(jsonDay.getString("summary"));
            day.setTemperatureMax(jsonDay.getDouble("temperatureMax"));
            day.setIcon(jsonDay.getString("icon"));
            day.setTime(jsonDay.getLong("time"));
            day.setTimezone(timezone);

            days[i] = day;

        }
        return days;
    }

    private Hour[] getHourlyForecast(String jsonData) throws JSONException {

        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");

        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");

        Hour[] hours = new Hour[data.length()];
        for (int i = 0;i<data.length();i++){

            JSONObject jsonHour = data.getJSONObject(i);
            Hour hour = new Hour();

            hour.setSummary(jsonHour.getString("summary"));
            hour.setTemperature(jsonHour.getDouble("temperature"));
            hour.setIcon(jsonHour.getString("icon"));
            hour.setTime(jsonHour.getLong("time"));
            hour.setTimezone(timezone);

            hours[i] = hour;
        }
        return hours;
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(info != null && info.isConnected()){

            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {

        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_dialog");
    }

    @OnClick(R.id.daily)
    public void startDailyActivity(View view){

        Intent intent = new Intent(this,DailyForecastActivity.class);
        intent.putExtra(DAILY_FORECAST,jForecast.getDailyForecast());
        startActivity(intent);
    }

    @OnClick(R.id.hourly)
    public void startHourlyActivity(View view){

        Intent intent = new Intent(this,HourlyForecastActivity.class);
        intent.putExtra(HOURLY_FORECAST,jForecast.getHourlyForecast());
        startActivity(intent);
    }

}
