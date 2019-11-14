package com.mohanad.weatherstate.model.Entity;

import java.util.Observable;

public class WeatherInfoObservable extends Observable {
    private WeatherInfo weatherInfo;
    private String error_message;

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
        setChanged();
        notifyObservers();
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
