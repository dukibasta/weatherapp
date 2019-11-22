package com.example.weatherapp.GPSProvider.LocationCallback;

import com.example.weatherapp.Models.GPSCoordinates;

public interface LocationProviderCallback {
    void onNewLocationAvailable(GPSCoordinates location);
}
