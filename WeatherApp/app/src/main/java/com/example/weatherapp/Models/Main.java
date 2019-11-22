package com.example.weatherapp.Models;

import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.WeatherApp;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Main implements Serializable {
    private static final String keyTemp = "temp";
    private static final String keyTempMin = "temp_min";
    private static final String keyTempMax = "temp_max";

    @SerializedName(keyTemp)
    public String temp;

    @SerializedName(keyTempMin)
    public String tempMin;

    @SerializedName(keyTempMax)
    public String tempMax;

    public String getCurrentTemperatureFormated(){
        return String.format(WeatherApp.getInstance().getString(R.string.temperature_format), temp);
    }

    public String getMinTemperatureFormated(){
        return String.format(WeatherApp.getInstance().getString(R.string.temperature_format), tempMin);
    }

    public String getMaxTemperatureFormated(){
        return String.format(WeatherApp.getInstance().getString(R.string.temperature_format), tempMax);
    }

    public String getMinMaxTemperatureFormated(){
        return String.format("%s / %s",getMinTemperatureFormated(),getMaxTemperatureFormated());
    }
}
