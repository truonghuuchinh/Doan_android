package com.baitap.doan.loadRecyclerView;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    public static String getWeather(String city) {
        Uri builtURI = Uri.parse("http://api.openweathermap.org/data/2.5/weather?").buildUpon()
                .appendQueryParameter("q", city)
                .appendQueryParameter("appid", "a05b9739ca3010d2e99bfff725cee7de")
                .build();
        try {
            URL requestURL = new URL(builtURI.toString());
            return callAPI(requestURL, "GET");
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static String callAPI(URL requestURL, String method) {
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.connect();
            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            result = convertISToString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    public static String convertISToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
                if (builder.length() == 0) {
                    return null;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return builder.toString();
    }
}
