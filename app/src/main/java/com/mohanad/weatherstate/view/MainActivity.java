package com.mohanad.weatherstate.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mohanad.weatherstate.R;
import com.mohanad.weatherstate.model.Entity.Main;
import com.mohanad.weatherstate.model.Entity.WeatherInfo;
import com.mohanad.weatherstate.model.Entity.WeatherInfoObservable;
import com.mohanad.weatherstate.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {
    private Group mainGroup;
    private TextView humidityTextView;
    private TextView pressureTextView;
    private TextView windTextView;
    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView highTemperatureTextView;
    private TextView lowTemperatureTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decelerations();
        MainViewModel mainViewModel=new MainViewModel();
        WeatherInfoObservable weatherInfoObservable =mainViewModel.getWeatherInfo();
        weatherInfoObservable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                WeatherInfoObservable observable = (WeatherInfoObservable) o;
                WeatherInfo weatherInfo = observable.getWeatherInfo();
                if (weatherInfo != null) {
                    Main mainWeatherInfo = weatherInfo.getMain();
                    humidityTextView.setText(String.valueOf(mainWeatherInfo.getHumidity()));
                    pressureTextView.setText(String.valueOf(mainWeatherInfo.getPressure()));
                    windTextView.setText(String.valueOf(weatherInfo.getWind().getSpeed()));
                    cityTextView.setText(weatherInfo.getName());
                    temperatureTextView.setText(getString(R.string.temperature, mainWeatherInfo.getTemp()));
                    highTemperatureTextView.setText(getString(R.string.temperature, mainWeatherInfo.getTempMax()));
                    lowTemperatureTextView.setText(getString(R.string.temperature, mainWeatherInfo.getTempMin()));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM", Locale.getDefault());
                    String formattedDate = simpleDateFormat.format(weatherInfo.getDt() * 1000);
                    dateTextView.setText(formattedDate);
                    mainGroup.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, observable.getError_message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void decelerations() {
        mainGroup = findViewById(R.id.group);
        humidityTextView = findViewById(R.id.text_view_humidity);
        pressureTextView = findViewById(R.id.text_view_pressure);
        windTextView = findViewById(R.id.text_view_wind);
        cityTextView = findViewById(R.id.text_city);
        temperatureTextView = findViewById(R.id.text_view_temperature);
        highTemperatureTextView = findViewById(R.id.text_view_high_temperature);
        lowTemperatureTextView = findViewById(R.id.text_view_low_temperature);
        dateTextView = findViewById(R.id.text_view_date);

    }


}
