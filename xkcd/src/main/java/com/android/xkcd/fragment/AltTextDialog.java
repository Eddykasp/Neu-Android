package com.android.xkcd.fragment;

import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.android.xkcd.R;

/**
 * Created by max on 14/07/17.
 */

public class AltTextDialog extends DialogFragment {

    public interface IAltTextInterfaceListener{
        void onPositiveClick();
        void onNegativeClick();
    }

    private String title;
    private String altText;
    private IAltTextInterfaceListener listener;

    public void setTitle(String title){
        this.title = title;
    }

    public void setAltText(String altText){
        this.altText = altText;
    }

    public void setListener(IAltTextInterfaceListener listener){
        this.listener = listener;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(altText).setTitle(title)
            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    listener.onPositiveClick();
                    dismiss();
                }

            })
            .setNegativeButton(R.string.goto_explain, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onNegativeClick();
                    dismiss();
                }
            });
        return builder.create();

    }
}
