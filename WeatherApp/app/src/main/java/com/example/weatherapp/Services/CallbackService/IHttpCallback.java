package com.example.weatherapp.Services.CallbackService;

import com.example.weatherapp.ErrorHandling.CustomError;

public interface IHttpCallback {
    void onSuccess(Object object);
    void onError(CustomError exception);
}
