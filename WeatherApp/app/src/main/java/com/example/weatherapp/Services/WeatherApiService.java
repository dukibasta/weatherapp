package com.example.weatherapp.Services;

import com.example.weatherapp.ErrorHandling.CustomError;
import com.example.weatherapp.Models.DailyResponse;
import com.example.weatherapp.Models.FilterModel;
import com.example.weatherapp.Models.HourlyResponse;
import com.example.weatherapp.Models.WeatherResponse;
import com.example.weatherapp.Services.CallbackService.IApiResponseCallback;
import com.example.weatherapp.Services.CallbackService.IHttpCallback;
import com.example.weatherapp.Services.Client.ApiClient;
import com.example.weatherapp.Services.WeatherApiInterfaces.WeatherApiInterface;
import com.example.weatherapp.Utilities.HttpUtilities;
import com.example.weatherapp.Utilities.WeatherApp;

import retrofit2.Call;

public class WeatherApiService {
    public static void getWeatherByCityName(FilterModel filter, IApiResponseCallback<WeatherResponse> responseCallback) {
        WeatherApiInterface service = ApiClient.getClient().create(WeatherApiInterface.class);
        Call<WeatherResponse> call = service.getWeatherByCityName(filter.cityName, WeatherApp.configuration.apiKey);
        HttpUtilities httpUtilities = new HttpUtilities();
        httpUtilities.post(call, new IHttpCallback() {
            @Override
            public void onSuccess(Object object) {
                WeatherResponse response = (WeatherResponse) object;
                responseCallback.onSuccess(response);
            }

            @Override
            public void onError(CustomError error) {
                responseCallback.onError(error);
            }
        });
    }

    public static void getForecastHourlyByCityName(FilterModel filter, IApiResponseCallback<HourlyResponse> responseCallback) {
        WeatherApiInterface service = ApiClient.getClient().create(WeatherApiInterface.class);
        Call<HourlyResponse> call = service.getForecastHourlyByCityName(filter.cityName, WeatherApp.configuration.apiKey);
        HttpUtilities httpUtilities = new HttpUtilities();
        httpUtilities.post(call, new IHttpCallback() {
            @Override
            public void onSuccess(Object object) {
                HourlyResponse response = (HourlyResponse) object;
                responseCallback.onSuccess(response);
            }

            @Override
            public void onError(CustomError error) {
                responseCallback.onError(error);
            }
        });
    }

    public static void getForecastDailyByCityName(FilterModel filter, IApiResponseCallback<DailyResponse> responseCallback) {
        WeatherApiInterface service = ApiClient.getClient().create(WeatherApiInterface.class);
        Call<DailyResponse> call = service.getForecastDailyByCityName(filter.cityName, filter.numberOfDays, WeatherApp.configuration.apiKey);
        HttpUtilities httpUtilities = new HttpUtilities();
        httpUtilities.post(call, new IHttpCallback() {
            @Override
            public void onSuccess(Object object) {
                DailyResponse response = (DailyResponse) object;
                responseCallback.onSuccess(response);
            }

            @Override
            public void onError(CustomError error) {
                responseCallback.onError(error);
            }
        });
    }

    public static void getWeatherByLocation(FilterModel filter, IApiResponseCallback<WeatherResponse> responseCallback) {
        WeatherApiInterface service = ApiClient.getClient().create(WeatherApiInterface.class);
        Call<WeatherResponse> call = service.getWeatherByLocation(filter.coordinates.latitude,filter.coordinates.longitude,WeatherApp.configuration.apiKey);
        HttpUtilities httpUtilities = new HttpUtilities();
        httpUtilities.post(call, new IHttpCallback() {
            @Override
            public void onSuccess(Object object) {
                WeatherResponse response = (WeatherResponse) object;
                responseCallback.onSuccess(response);
            }

            @Override
            public void onError(CustomError error) {
                responseCallback.onError(error);
            }
        });
    }

    public static void getForecastHourlyByLocation(FilterModel filter, IApiResponseCallback<HourlyResponse> responseCallback) {
        WeatherApiInterface service = ApiClient.getClient().create(WeatherApiInterface.class);
        Call<HourlyResponse> call = service.getForecastHourlyByLocation(filter.coordinates.latitude,filter.coordinates.longitude,WeatherApp.configuration.apiKey);
        HttpUtilities httpUtilities = new HttpUtilities();
        httpUtilities.post(call, new IHttpCallback() {
            @Override
            public void onSuccess(Object object) {
                HourlyResponse response = (HourlyResponse) object;
                responseCallback.onSuccess(response);
            }

            @Override
            public void onError(CustomError error) {
                responseCallback.onError(error);
            }
        });
    }

    public static void getForecastDailyByLocation(FilterModel filter, IApiResponseCallback<DailyResponse> responseCallback) {
        WeatherApiInterface service = ApiClient.getClient().create(WeatherApiInterface.class);
        Call<DailyResponse> call = service.getForecastDailyByLocation(filter.coordinates.latitude,filter.coordinates.longitude,filter.numberOfDays, WeatherApp.configuration.apiKey);
        HttpUtilities httpUtilities = new HttpUtilities();
        httpUtilities.post(call, new IHttpCallback() {
            @Override
            public void onSuccess(Object object) {
                DailyResponse response = (DailyResponse) object;
                responseCallback.onSuccess(response);
            }

            @Override
            public void onError(CustomError error) {
                responseCallback.onError(error);
            }
        });
    }
}
