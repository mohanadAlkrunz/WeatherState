package com.mohanad.weatherstate.viewmodel;

import androidx.annotation.NonNull;

import com.mohanad.weatherstate.model.Entity.WeatherInfo;
import com.mohanad.weatherstate.model.Entity.WeatherInfoObservable;
import com.mohanad.weatherstate.model.Network.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel {
    private NetworkUtils mNetworkUtils;
    private WeatherInfoObservable weatherInfoObservable;

    public MainViewModel(){
        mNetworkUtils = NetworkUtils.getInstance();
        weatherInfoObservable = new WeatherInfoObservable();
    }

    public WeatherInfoObservable getWeatherInfo() {
        Call<WeatherInfo> call = mNetworkUtils.getWeatherInfoApiInterface().getWeatherInfo(mNetworkUtils.getQueryMap());
        call.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(@NonNull Call<WeatherInfo> call, @NonNull Response<WeatherInfo> response) {
                System.out.println("Received!");
                WeatherInfo weatherInfo = response.body();
                weatherInfoObservable.setWeatherInfo(weatherInfo);
            }

            @Override
            public void onFailure(@NonNull Call<WeatherInfo> call, @NonNull Throwable t) {
                weatherInfoObservable.setError_message(t.getMessage());
            }
        });
        return weatherInfoObservable;
    }
}
