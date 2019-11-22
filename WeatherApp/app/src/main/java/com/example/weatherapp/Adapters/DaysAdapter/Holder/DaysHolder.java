package com.example.weatherapp.Adapters.DaysAdapter.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Models.Daily;
import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.WeatherApp;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaysHolder extends RecyclerView.ViewHolder {

    public
    @Nullable
    @BindView(R.id.tvDate)
    TextView tvDate;

    public
    @Nullable
    @BindView(R.id.ivIcon)
    ImageView ivIcon;

    public
    @Nullable
    @BindView(R.id.tvTemperature)
    TextView tvTemperature;

    public DaysHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populateHolder(Daily daily){
        tvDate.setText(daily.getDateFormatEddMMM());
        Picasso.with(WeatherApp.getInstance()).load(daily.getWeahterIconUrl()).error(R.drawable.no_image).into(ivIcon);
        tvTemperature.setText(daily.main.getMinMaxTemperatureFormated());

    }
}
