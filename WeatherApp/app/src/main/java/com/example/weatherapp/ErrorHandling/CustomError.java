package com.example.weatherapp.ErrorHandling;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class CustomError {

    public int errorCode;
    public String errorMessage;

    public CustomError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        if (isValidJSON(errorMessage)){
            Error error = new Gson().fromJson(errorMessage,Error.class);
            if (error !=null){
                this.errorMessage = error.message;
                return;
            }
        }
        this.errorMessage = errorMessage;
    }

    private boolean isValidJSON(String requestBody) {
        try {
            new JSONObject(requestBody);
        } catch (JSONException jsonEx) {
            return false;
        }
        return true;
    }

    class Error implements Serializable{

        @SerializedName("message")
        public String message;
    }
}