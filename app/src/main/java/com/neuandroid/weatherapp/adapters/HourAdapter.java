package com.neuandroid.weatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neuandroid.weatherapp.R;
import com.neuandroid.weatherapp.weather.Hour;

/**
 * Created by mac on 21/07/2017.
 */

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private Hour[] jHours;
    private Context jContext;
    public HourAdapter(Context context, Hour[] hours){

        jHours = hours;
        jContext = context;
    }
    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item,parent,false);
        HourViewHolder viewHolder = new HourViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {

        holder.bindHour(jHours[position]);
    }

    @Override
    public int getItemCount() {
        return jHours.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView jTimeLabel;
        public TextView jSummaryLabel;
        public TextView jTemperatureLabel;
        public ImageView jIconImageView;
        public HourViewHolder(View itemView) {
            super(itemView);

            jTimeLabel = (TextView)itemView.findViewById(R.id.timeLabel);
            jSummaryLabel = itemView.findViewById(R.id.summaryLabel);
            jTemperatureLabel = itemView.findViewById(R.id.tempLabel);
            jIconImageView = itemView.findViewById(R.id.iconimageView);

            itemView.setOnClickListener(this);
        }

        public void bindHour(Hour hour){

            jTimeLabel.setText(hour.getHour());
            jSummaryLabel.setText(hour.getSummary());
            jTemperatureLabel.setText(hour.getTemperature() + "");
            jIconImageView.setImageResource(hour.getIconId());
        }

        @Override
        public void onClick(View view) {

            String time = jTimeLabel.getText().toString();
            String temp = jTemperatureLabel.getText().toString();
            String summary = jSummaryLabel.getText().toString();
            String message = String.format("At %s it will be %s and %s",time,temp,summary);

            Toast.makeText(jContext,message,Toast.LENGTH_LONG).show();
        }
    }
}
