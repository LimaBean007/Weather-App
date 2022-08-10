package com.kiellenaidu.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FiveDayWeather extends Fragment {

    static FiveDayWeather instance;

    private FiveDayWeather() {
        // Required empty public constructor
    }

    public static FiveDayWeather getInstance() {
        {
            if (instance == null) {
                return new FiveDayWeather();
            }
            return instance;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_five_day_weather, container, false);
    }
}