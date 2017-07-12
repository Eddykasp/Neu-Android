package com.android.neuandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by max on 12/07/17.
 */

public class SecondActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView();
    }

    private void closeActivity() {
        Intent intent = new Intent();
        intent.putExtra("temp",21);
        setResult(RESULT_OK, intent);
    }
}
