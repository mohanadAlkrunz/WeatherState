package com.mohanad.weatherstate.model.Network;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private String BASE_URL="https://api.openweathermap.org/data/2.5/";
    private String APP_ID="8df01e3fda5f48ddbb999216c9293c37";
    private static NetworkUtils instance;
    private WeatherInfoApiInterface weatherInfoApiInterface;


    private NetworkUtils(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL) .addConverterFactory(GsonConverterFactory.create()).build();
        weatherInfoApiInterface=retrofit.create(WeatherInfoApiInterface.class);
    }

    public static NetworkUtils getInstance() {
       if(instance == null){
           instance=new NetworkUtils();
       }
       return instance;
    }

    public WeatherInfoApiInterface getWeatherInfoApiInterface() {
        return weatherInfoApiInterface;
    }

    public Map<String, String> getQueryMap() {
        Map<String,String> map = new HashMap<>();
        map.put("q","Gaza Strip");
        map.put("units","metric");
        map.put("lang", "ar");
        map.put("appid",APP_ID);
        return map;
    }

}
