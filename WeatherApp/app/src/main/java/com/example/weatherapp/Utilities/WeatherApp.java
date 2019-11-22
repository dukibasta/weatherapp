package com.example.weatherapp.Utilities;

import android.app.Application;
import android.content.Context;

import com.example.weatherapp.R;

public class WeatherApp extends Application {

    public static Configuration configuration;
    private static Context mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setupConfiguration();
    }

    private void setupConfiguration(){
        configuration = new Configuration();
        configuration.apiUrl = getString(R.string.api_url);
        configuration.apiKey = getString(R.string.api_key);
    }

    public static synchronized Context getInstance() {
        return mInstance;
    }

}
