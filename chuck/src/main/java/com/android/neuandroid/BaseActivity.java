package com.android.neuandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by max on 12/07/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Activity_Lifecycle", "oncreate " + this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
