package com.dawud.weather.controllers;

import com.dawud.weather.domain.Weather;
import com.dawud.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Weather getWeather(@RequestParam(value = "lat", required=true) String latitude,
                              @RequestParam(value = "lon", required=true) String longitude,
                              @RequestParam(value = "date", required=false) String date) throws IOException, ParseException {

        Double latitudeD = Double.parseDouble(latitude);
        Double longitudeD = Double.parseDouble(longitude);
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date weatherDate = parser.parse(date);

        Weather weather = weatherService.getWeather(latitudeD, longitudeD, weatherDate);
        return weather;
    }


}
