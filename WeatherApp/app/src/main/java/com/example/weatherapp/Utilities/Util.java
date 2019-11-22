package com.example.weatherapp.Utilities;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.example.weatherapp.R;

public class Util {
    public static FragmentTransaction getAnimatedFragmentTransaction(FragmentTransaction fragmentTransaction) {
        return fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                R.anim.fade_in, R.anim.fade_out);
    }

    static Toast toast;
    public static void createToastMessage(Context context, String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
//        if (toast == null) {
//            toast = Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT);
//        }
//        if (!toast.getView().isShown()) {
//            toast.setText(toastMessage);
//            toast.show();
//        }
    }
}
