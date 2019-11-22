package com.example.weatherapp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class HourlyResponse implements Serializable {
    private static final String keyList = "list";

    @SerializedName(keyList)
    public ArrayList<Hourly> hourlies;

}
