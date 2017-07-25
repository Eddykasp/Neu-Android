package com.neuandroid.weatherapp.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.neuandroid.weatherapp.R;

/**
 * Created by mac on 18/07/2017.
 */

public class AlertDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.error_title)
                .setMessage(R.string.error_msg)
                .setPositiveButton(R.string.error_ok,null);
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }
}
