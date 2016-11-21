package com.dawud.events.service;

import com.dawud.events.domain.Weather;
import com.dawud.events.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public Weather getWeather(String latitude, String longitude, String date) throws IOException {
        Weather weather = weatherRepository.getWeather(latitude, longitude, date);
        return weather;
    }
}
