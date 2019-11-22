package com.example.weatherapp.Models;

import android.text.format.DateFormat;

import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.WeatherApp;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Daily implements Serializable {
    private static final String keyMain = "temp";
    private static final String keyWeather = "weather";
    private static final String keyDt = "dt";

    @SerializedName(keyMain)
    public Temp main;

    @SerializedName(keyWeather)
    public ArrayList<Weather> weather;

    @SerializedName(keyDt)
    public long timestamp;

    public String getWeahterIconUrl(){
        if (weather == null || weather.isEmpty()){
            return WeatherApp.getInstance().getString(R.string.empty_string_preview);
        }
        return weather.get(0).getIconUrl();
    }

    public String getDateFormatEddMMM(){

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timestamp * 1000L);
        String date = DateFormat.format("E, dd MMM", cal).toString();
        return date;

    }
}
