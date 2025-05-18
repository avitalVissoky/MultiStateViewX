package com.example.multistateviewxlibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.avitaliskhakov.multistateviewx.MultiStateNavigator;
import com.avitaliskhakov.multistateviewx.MultiStateView;
import com.avitaliskhakov.multistateviewx.State;
import com.avitaliskhakov.multistateviewx.TransitionType;

public class MainActivity extends AppCompatActivity {

    private MultiStateView multiStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multiStateView = findViewById(R.id.multiStateView);

        View contentView = LayoutInflater.from(this).inflate(R.layout.content_layout, null);
        multiStateView.setContentView(contentView);

        multiStateView.setOnRetryClickListener(v -> {
            multiStateView.setState(State.LOADING);
            v.postDelayed(() -> multiStateView.setState(State.CONTENT), 2000);
        });

        multiStateView.setState(State.EMPTY);
    }


    public void showLoading(View v) {
        multiStateView.setState(State.LOADING);
    }

    public void showEmpty(View v) {
        multiStateView.setState(State.EMPTY);
    }

    public void showError(View v) {
        multiStateView.setState(State.ERROR);
    }

    public void showContent(View v) {
        multiStateView.setState(State.CONTENT);
    }

    public void fadeToSecondActivity(View v) {
        MultiStateNavigator.startActivity(this, SecondActivity.class, TransitionType.FADE);
    }

    public void slideToSecondActivity(View v) {
        MultiStateNavigator.startActivity(this, SecondActivity.class, TransitionType.SLIDE);
    }

    public void noneToSecondActivity(View v) {
        MultiStateNavigator.startActivity(this, SecondActivity.class, TransitionType.NONE);
    }

    public void showCustomLoading(View v) {
        multiStateView.setCustomLayout(State.LOADING, R.layout.custom_loading_spinner);
        multiStateView.setState(State.LOADING);
    }
    public void resetToDefaultLoading(View v) {
        multiStateView.resetToDefault(State.LOADING);
        multiStateView.setState(State.LOADING);
    }
}
