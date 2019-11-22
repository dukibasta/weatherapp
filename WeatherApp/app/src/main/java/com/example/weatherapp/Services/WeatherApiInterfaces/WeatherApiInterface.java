package com.example.weatherapp.Services.WeatherApiInterfaces;

import com.example.weatherapp.Models.DailyResponse;
import com.example.weatherapp.Models.HourlyResponse;
import com.example.weatherapp.Models.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {

    @GET("/data/2.5/weather?")
    Call<WeatherResponse> getWeatherByCityName(@Query("q") String cityName, @Query("appid") String appId);

    @GET("/data/2.5/forecast?")
    Call<HourlyResponse> getForecastHourlyByCityName(@Query("q") String cityName, @Query("appid") String appId);

    @GET("/data/2.5/forecast/daily?")
    Call<DailyResponse> getForecastDailyByCityName(@Query("q") String cityName, @Query("cnt") String cnt, @Query("appid") String appId);

    @GET("/data/2.5/weather?")
    Call<WeatherResponse> getWeatherByLocation(@Query("lat") double latitude, @Query("lon") double logitude, @Query("appid") String appId);

    @GET("/data/2.5/forecast?")
    Call<HourlyResponse> getForecastHourlyByLocation(@Query("lat") double latitude, @Query("lon") double logitude, @Query("appid") String appId);

    @GET("/data/2.5/forecast/daily?")
    Call<DailyResponse> getForecastDailyByLocation(@Query("lat") double latitude, @Query("lon") double logitude, @Query("cnt") String cnt, @Query("appid") String appId);

}
