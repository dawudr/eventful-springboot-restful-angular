package com.dawud.events.controllers;

import com.dawud.events.domain.Event;
import com.dawud.events.domain.Weather;
import com.dawud.events.domain.util.EventConstants;
import com.dawud.events.service.EventService;
import com.dawud.events.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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
                                               @RequestParam(value = "date", required=false) String date) throws IOException {
        Weather weather = weatherService.getWeather(latitude, longitude, date);
        return weather;
    }


}
