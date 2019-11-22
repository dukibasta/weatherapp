package com.example.weatherapp.Models;

import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.WeatherApp;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Hourly implements Serializable {
    private static final String keyMain = "main";
    private static final String keyWeather = "weather";
    private static final String keyDtTxt = "dt_txt";

    @SerializedName(keyMain)
    public Main main;

    @SerializedName(keyWeather)
    public ArrayList<Weather> weather;

    @SerializedName(keyDtTxt)
    public String date;

    public String getWeahterIconUrl(){
        if (weather == null || weather.isEmpty()){
            return WeatherApp.getInstance().getString(R.string.empty_string_preview);
        }
        return weather.get(0).getIconUrl();
    }

    public String getHour(){
        try {
            SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
           return hourFormat.format(serverDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return WeatherApp.getInstance().getString(R.string.empty_string_preview);
        }

    }
}
