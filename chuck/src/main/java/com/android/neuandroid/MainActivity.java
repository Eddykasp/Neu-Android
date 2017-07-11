package com.android.neuandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView chuckText;
    private static final String API_QUERY = "http://api.icndb.com/jokes/random?limitTo=[explicit]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chuckText = (TextView) findViewById(R.id.tv_chuck);
        chuckText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                loadChuckJoke();
            }
        });
    }

    private void loadChuckJoke(){
        new ChuckJokeTask().execute(API_QUERY);
    }

    private class ChuckJokeTask extends AsyncTask<String, Object, String>{

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            try{
                URL url = new URL(params[0]);
                result = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            chuckText.setText("Loading...");
        }

        @Override
        protected void onPostExecute(String s) {
            chuckText.setText(s);
        }
    }
}
