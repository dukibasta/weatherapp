package com.example.weatherapp.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.weatherapp.ErrorHandling.INoNetworkListener;
import com.example.weatherapp.R;
import com.example.weatherapp.Utilities.InternetConnectivityUtil;
import com.example.weatherapp.Utilities.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoNetworkView extends Dialog {

    private INoNetworkListener listener;
    private Button btnRefresh;

    public NoNetworkView(Context context, INoNetworkListener listener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_no_internet_connection);
        setup();
    }

    private void setup() {
        ButterKnife.bind(this);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    @OnClick(R.id.btnRefresh)
    public void refreshClick(){
        if (!InternetConnectivityUtil.isNetworkAvailable()) {
            Util.createToastMessage(getContext(), getContext().getResources().getString(R.string.http__utilities_no_internet_connection));
            return;
        }
        listener.onClickRefresh();
        dismiss();
    }
    private View.OnClickListener btnRefreshClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (!InternetConnectivityUtil.isNetworkAvailable()) {
                Util.createToastMessage(getContext(), getContext().getResources().getString(R.string.http__utilities_no_internet_connection));
                return;
            }
            listener.onClickRefresh();
            dismiss();
        }
    };

    @Override
    public void onBackPressed() {

    }
}

