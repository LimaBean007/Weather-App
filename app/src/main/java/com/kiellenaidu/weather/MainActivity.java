package com.kiellenaidu.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AccuWeatherURL";

    Fragment tideFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment today = FiveDayWeather.getInstance();
        tideFragment = new TideFragment();

        URL accuWeatherURL = NetworkUtil.BuildURL();
        Log.i(TAG, " On Create: " + accuWeatherURL);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.weather_frame, today);
        transaction.replace(R.id.tide_frame,tideFragment);
        transaction.commit();

    }
}