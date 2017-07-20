package com.neuandroid.refreshed;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.neuandroid.util.NetworkUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

public class NewsQueryTask extends AsyncTask<URL, Object, String> {

    public interface IAsyncTaskListener {
        void onPreExecute();
        void onPostExecute(Serializable result);
    }

    private IAsyncTaskListener listener;

    public NewsQueryTask(IAsyncTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        listener.onPreExecute();
    }

    @Override
    protected String doInBackground(URL... params) {
        String result = null;

        try {
            URL url = params[0];
            result = NetworkUtils.getResponseFromHttpUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        NewsListBean newsList = new Gson().fromJson(result, NewsListBean.class);
        listener.onPostExecute(newsList);
    }

}