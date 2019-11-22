package com.example.weatherapp.ErrorHandling;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp.Dialogs.NoNetworkView;
import com.example.weatherapp.Enums.HttpCodeEnum;
import com.example.weatherapp.Utilities.Util;

public class ErrorHandling {
    public static void handlingError(Context context, CustomError customError, TextView validationText) {
        HttpCodeEnum codeEnum = HttpCodeEnum.getHttpCodeType(customError.errorCode);
        switch (codeEnum) {
            case Internal: {
                Util.createToastMessage(context, customError.errorMessage);
            }
            break;
            case NoInternet: {
                if (validationText != null) {
                    validationText.setVisibility(View.VISIBLE);
                    validationText.setText(customError.errorMessage);
                } else {
                    Util.createToastMessage(context, customError.errorMessage);
                }
            }
            break;
            case Unauthorized: {
                Util.createToastMessage(context, customError.errorMessage);
            }
            break;
            case NotFound: {
                if (validationText != null) {
                    validationText.setVisibility(View.VISIBLE);
                    validationText.setText(customError.errorMessage);
                } else {
                    Util.createToastMessage(context, customError.errorMessage);
                }
            }
            break;
            case Default: {
                Util.createToastMessage(context, customError.errorMessage);
            }
            break;
        }
    }
}
