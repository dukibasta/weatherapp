package com.example.weatherapp.Services.CallbackService;

import com.example.weatherapp.ErrorHandling.CustomError;

public interface IApiResponseCallback<T> {
    void onSuccess(T object);
    void onError(CustomError exception);
}
