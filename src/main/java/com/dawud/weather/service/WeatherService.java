package com.dawud.weather.service;

import com.dawud.weather.domain.Weather;
import com.dawud.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    public Weather getWeather(Double latitude, Double longitude, Date date) {
        Weather weather = null;
        try {
            weather = weatherRepository.getWeather(latitude, longitude, date);
        } catch (IOException e) {
            e.printStackTrace();
            Weather emptyWeather = new Weather();
            emptyWeather.setTemp("No data");
            emptyWeather.setDescription("No data");
            emptyWeather.setDt_txt(new Date());
            return emptyWeather;
        }
        return weather;
    }
}
