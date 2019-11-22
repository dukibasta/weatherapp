package com.example.weatherapp.Adapters.HourlyAdapter.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Models.Hourly;
import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.WeatherApp;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyHolder extends RecyclerView.ViewHolder {

    public
    @Nullable
    @BindView(R.id.tvDateFrom)
    TextView tvDateFrom;

    public
    @Nullable
    @BindView(R.id.ivIcon)
    ImageView ivIcon;

    public
    @Nullable
    @BindView(R.id.tvTemperature)
    TextView tvTemperature;

    public HourlyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populateHolder(Hourly hourly){
        tvDateFrom.setText(hourly.getHour());
        Picasso.with(WeatherApp.getInstance()).load(hourly.getWeahterIconUrl()).error(R.drawable.no_image).into(ivIcon);
        tvTemperature.setText(hourly.main.getCurrentTemperatureFormated());

    }
}
