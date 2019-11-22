package com.example.weatherapp.Models;

import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.WeatherApp;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherResponse implements Serializable {
    private static final String keyName ="name";
    private static final String keyWeather ="weather";
    private static final String keyMain ="main";

    @SerializedName(keyName)
    public String name;

    @SerializedName(keyWeather)
    public ArrayList<Weather> weather;

    @SerializedName(keyMain)
    public Main main;

    public String getMainWeatherDescription(){
        if (weather == null || weather.isEmpty()){
            return WeatherApp.getInstance().getString(R.string.empty_string_preview);
        }
        return weather.get(0).main;
    }

}
