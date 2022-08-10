package com.kiellenaidu.weather;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {
    private static final String BASE_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/305605";
    private static final String API_KEY = "efViCicCErMXNqR8I04uC9gNfvy2ZRed";
    private static final String PARAM_API_KEY = "apikey";
    private static final String METRIC_PARAM = "metric";
    private static final String METRIC_VALUE = "true";
    private static String TAG = " NETWORK_UTIL";


    public static URL BuildURL() {
        Uri uri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(PARAM_API_KEY, API_KEY).appendQueryParameter(METRIC_PARAM, METRIC_VALUE).build();
        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;


    }

    public static String getResponse(URL url) throws IOException {

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        //Pull in existing java class
        try {
            //Gets input from the http GET request response
            InputStream in = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(in);

            // Delimiter is used to find next line JSON
            scanner.useDelimiter("//A");
            //Checks if there is extra lines
            boolean hasInput = scanner.hasNext();


            if(hasInput)
            {
                //If there is return the next line
                return  scanner.next();
            } else {
                //If there isnt extra lines return null
                Log.i(TAG, "getResponse: " + scanner.next());
                return null;
            }

        } finally {
            httpURLConnection.disconnect();
        }
    }


}



