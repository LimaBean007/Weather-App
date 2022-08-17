package com.kiellenaidu.weather;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class FiveDayWeather extends Fragment {

    static FiveDayWeather instance;
    String TAG = "url";

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
        View view = inflater.inflate(R.layout.fragment_five_day_weather, container, false);
        URL url = NetworkUtil.BuildURL();
        Log.d(TAG, "onCreateView: " + url);
        new GetWeatherData().execute(url);
        return view;
    }

    class GetWeatherData extends AsyncTask<URL, Void, String> {

        private String TAG = "JSONDATA";
        ArrayList<Forecast> dailyWeatherList = new ArrayList();

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String weatherData = null;
            try {
                weatherData = NetworkUtil.getResponse(url);
                Log.d(TAG, "doInBackground: " + weatherData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return weatherData;
        }

        @Override
        protected void onPostExecute(String weatherData) {
            if (weatherData == null) {
                consumedJSON(weatherData);
            }
            super.onPostExecute(weatherData);

        }

        public ArrayList<Forecast> consumedJSON(String weatherData) {
            if (dailyWeatherList != null) {
                dailyWeatherList.clear();
            }
            if (weatherData != null) {
                try {
                    JSONObject rootWeatherData = new JSONObject(weatherData);
                    JSONArray todayWeather = rootWeatherData.getJSONArray("DailyForecasts");
                    for (int i = 0; i < todayWeather.length(); i++) {
                        Forecast newForecast = new Forecast();

                        JSONObject dailyWeather = todayWeather.getJSONObject(i);
                        String date = dailyWeather.getString("date");
                        newForecast.setDate(date);
                        Log.i(TAG, "Daily date is : " + date);

                        JSONObject dailytemp = dailyWeather.getJSONObject("Temperature");
                        JSONObject minTemp = dailytemp.getJSONObject("Minimum");
                        String minTemperature = minTemp.getString("Value");
                        Log.i(TAG, "minTemperature : " + minTemperature);
                        JSONObject maxTemp = dailytemp.getJSONObject("Maximum");
                        String maxTemperature = maxTemp.getString("Value");
                        Log.i(TAG, "maxTemperature" + maxTemperature);
                        newForecast.setMaxTemp(maxTemperature);
                        newForecast.setMinTemp(minTemperature);

                        JSONObject day = dailyWeather.getJSONObject("Day");
                        String iconValue = day.getString("IconePhrase");
                        newForecast.setURL(iconValue);
                        Log.i(TAG, "iconValue" + iconValue);
                        dailyWeatherList.add(newForecast);
                    }
                    Log.d(TAG, "consumedJSON: " + rootWeatherData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    }
}