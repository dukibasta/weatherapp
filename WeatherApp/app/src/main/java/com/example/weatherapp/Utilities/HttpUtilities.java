package com.example.weatherapp.Utilities;

import com.example.weatherapp.Enums.HttpCodeEnum;
import com.example.weatherapp.ErrorHandling.CustomError;
import com.example.weatherapp.R;
import com.example.weatherapp.Services.CallbackService.IHttpCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpUtilities {
    public <T extends Object> void post(Call<T> call, final IHttpCallback callback) {

        if (!InternetConnectivityUtil.isNetworkAvailable()) {
            callback.onError(new CustomError(HttpCodeEnum.NoInternet.value, WeatherApp.getInstance().getResources().getString(R.string.http__utilities_no_internet_connection)));
            return;
        }

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                       String error = response.errorBody().string();
                       int errorCode = response.code();
                        callback.onError(new CustomError(errorCode, error));
                    } catch (IOException e) {
                        callback.onError(new CustomError(HttpCodeEnum.Internal.value,WeatherApp.getInstance().getString(R.string.http_utilities_error_occurred)));
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onError(new CustomError(HttpCodeEnum.Internal.value,t.getMessage()));
            }
        });
    }
}
