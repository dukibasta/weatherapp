package com.example.weatherapp.Models;

public class GPSCoordinates {
    public double longitude;
    public double latitude;

    public GPSCoordinates(double theLatitude, double theLongitude) {
        longitude = theLongitude;
        latitude = theLatitude;
    }
}
