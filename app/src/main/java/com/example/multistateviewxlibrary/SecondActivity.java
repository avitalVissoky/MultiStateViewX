package com.example.multistateviewxlibrary;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.avitaliskhakov.multistateviewx.MultiStateNavigator;
import com.avitaliskhakov.multistateviewx.TransitionType;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
    }

    public void goBack(View view) {
        // מעבר חזור עם אנימציה
        MultiStateNavigator.finishWithTransition(this, TransitionType.SLIDE);
    }
}