package com.example.weatherapp.Enums;

public enum HttpCodeEnum {
    NoInternet(666),
    Unauthorized(401),
    NotFound(404),
    Internal(500),
    Default(-1);

    public int value;

    HttpCodeEnum(int value) {
        this.value = value;
    }

    public static HttpCodeEnum getHttpCodeType(int code) {
        if (code == NoInternet.value) {
            return NoInternet;
        } else if (code == Unauthorized.value) {
            return Unauthorized;
        } else if (code == NotFound.value) {
            return NotFound;
        } else if (code == Internal.value) {
            return Internal;
        }
        return Default;
    }
}
