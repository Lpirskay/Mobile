package com.rodion.meditationapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rodion.meditationapp.R;
import com.rodion.meditationapp.presentation.view.start.StartActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MeditationApp);
        StartActivity.open(this);
    }
}