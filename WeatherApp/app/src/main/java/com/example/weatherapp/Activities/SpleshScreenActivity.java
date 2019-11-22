package com.example.weatherapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.ContextThemeWrapper;

import androidx.annotation.Nullable;

import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.PermissionUtilities;

public class SpleshScreenActivity extends BaseActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spleshscreen);
        setup();
    }

    private void setup() {
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          checkPermissionBeforeStartApp();
                                      }
                                  }

                , SPLASH_TIME_OUT);
    }

    private void checkPermissionBeforeStartApp() {
        if (!PermissionUtilities.isLocationPermissionGranted(this)) {
            return;
        }

        startMainActivity();
    }


    private void startMainActivity() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionUtilities.REQUEST_CODE_ASK_PERMISSIONS_FOR_lOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermissionBeforeStartApp();
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                    ContextThemeWrapper ctw = new ContextThemeWrapper(this, android.R.style.Theme_DeviceDefault_Light);
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
                    builder.setMessage(getString(R.string.splesh_screen_activity_allow_weatherapp_to_access_this_device_location));
                    builder.setPositiveButton(getString(R.string.splesh_screen_activity_allow), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                            goToSettings();
                        }
                    });
                    builder.setNegativeButton(getString(R.string.splesh_screen_activity_deny), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                break;
            default:
        }
    }

    private void goToSettings() {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myAppSettings);
    }
}
