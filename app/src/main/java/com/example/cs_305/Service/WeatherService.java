package com.example.cs_305.Service;

import com.example.cs_305.Tool.HTTPRequests;
import com.example.cs_305.Tool.Location;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherService {
    private static final String KEY = "d7587ed1889cac76b43882f283133296";

    public static String getWeather(Location location) {
        if (location != null) {
            String result = HTTPRequests.downloadUrl("https://api.openweathermap.org/data/2.5/onecall?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&appid=" + KEY);
            try {
                JSONObject json = new JSONObject(result);
                JSONObject current = json.getJSONObject("current");
                JSONArray weather = current.getJSONArray("weather");
                result = "" + (Math.round((current.getDouble("temp") - 273.15) * 100)/100) + "°C";
                for (int max = weather.length(), i = 0; i < max; ++i) {
                    result += ", " + weather.getJSONObject(i).getString("description");
                }
                return result;
            } catch (JSONException e) { }
        }
        return null;
    }
}