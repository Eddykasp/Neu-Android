package com.neuandroid.weather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.neuandroid.refreshed.R;
import com.neuandroid.weather.weather.Day;

/**
 * Created by mac on 20/07/2017.
 */

public class DayAdapter extends BaseAdapter {

    private Context jContext;
    private Day[] jDays;

    public DayAdapter(Context context, Day[] days){

        jContext = context;
        jDays = days;
    }

    @Override
    public int getCount() {
        return jDays.length;
    }

    @Override
    public Object getItem(int position) {
        return jDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View conterView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(conterView == null){

            conterView = LayoutInflater.from(jContext).inflate(R.layout.daily_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.iconImageView = (ImageView) conterView.findViewById(R.id.iconImageView);
            viewHolder.temperatureLabel = (TextView) conterView.findViewById(R.id.temperatureLabel);
            viewHolder.dayLabel = (TextView) conterView.findViewById(R.id.dayName);
            conterView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) conterView.getTag();
        }
        Day day = jDays[position];
        viewHolder.iconImageView.setImageResource(day.getIconId());
        viewHolder.temperatureLabel.setText(day.getTemperatureMax() + "");
        viewHolder.dayLabel.setText(day.getDayOfWeek());

        return conterView;
    }

    private static class ViewHolder{

        ImageView iconImageView;//public by default
        TextView temperatureLabel;
        TextView dayLabel;
    }

    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
