package com.example.weatherapp.Utilities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.content.ContentValues.TAG;

public class PermissionUtilities {

    public static final int REQUEST_CODE_ASK_PERMISSIONS_FOR_lOCATION = 100;
    public static boolean isLocationPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (((AppCompatActivity)context).checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(((AppCompatActivity)context), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS_FOR_lOCATION);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }
}
