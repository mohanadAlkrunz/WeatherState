package com.mohanad.weatherstate.model.Network;

import com.mohanad.weatherstate.model.Entity.WeatherInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherInfoApiInterface {

    @GET("weather")
    Call<WeatherInfo> getWeatherInfo(@QueryMap Map<String, String> params);
}
