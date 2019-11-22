package com.example.weatherapp.Dialogs;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.weatherapp.R;
import com.wang.avi.AVLoadingIndicatorView;

public class ProgressBarDialog extends DialogFragment {
    private Window window;
    boolean hasAddedOnView = true;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        window = getDialog().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (true) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_progress_bar, container, false);
        AVLoadingIndicatorView avLoadingIndicatorView = view.findViewById(R.id.avi);
        avLoadingIndicatorView.show();
        return view;
    }

    private View decorView;

    @Override
    public void onStart() {
        super.onStart();
        decorView = getDialog()
                .getWindow()
                .getDecorView();

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(decorView,
                PropertyValuesHolder.ofFloat("alpha", 0.5f, 1.0f));
        scaleDown.setDuration(100);
        scaleDown.start();

        if (!hasAddedOnView && getDialog() != null) {
            getDialog().dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    return true;
                }
                return false;
            }
        });
    }

    public void dismiss() {
        if (decorView == null) {
            hasAddedOnView = false;
            return;
        }
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(decorView,
                PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f));
        scaleDown.setDuration(500);
        scaleDown.start();
        scaleDown.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (getDialog() != null &&
                        getView() != null) {
                    getDialog().dismiss();
                    getView().setFocusableInTouchMode(false);
                    getView().clearFocus();
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

}

