package com.example.weatherapp.Activities;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.Dialogs.ProgressBarDialog;
import com.example.weatherapp.Utilities.Util;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {
    private ProgressBarDialog progressBarDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        progressBarDialog = new ProgressBarDialog();
    }

    public void startProgressBar() {
        if (!progressBarDialog.isAdded()) {
            progressBarDialog.show(Util.getAnimatedFragmentTransaction(getSupportFragmentManager().beginTransaction()), "");
        }
    }

    public void endProgressBar() {
        progressBarDialog.dismiss();
    }

}