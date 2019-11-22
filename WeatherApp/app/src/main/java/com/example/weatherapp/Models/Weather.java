package com.example.weatherapp.Models;

import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.WeatherApp;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Weather implements Serializable {
    private static final String keyMain = "main";
    private static final String keyIcon = "icon";

    @SerializedName(keyMain)
    public String main;

    @SerializedName(keyIcon)
    public String icon;

    public String getIconUrl(){
        return String.format(WeatherApp.getInstance().getString(R.string.icons_url),
                WeatherApp.configuration.apiUrl,
                icon);
    }
}
