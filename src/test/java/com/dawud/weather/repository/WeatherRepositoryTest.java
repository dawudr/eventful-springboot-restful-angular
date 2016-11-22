package com.dawud.weather.repository;

import com.dawud.Application;
import com.dawud.weather.domain.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class WeatherRepositoryTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void testGetWeather() throws Exception {
        String date = "2016-11-21 06:00:00";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date weatherDate = parser.parse(date);
        String description = "light rain";
        String temp = "29";
        Double latitude = 35d;
        Double longitute = 139d;


        Weather output = weatherRepository.getWeather(latitude, longitute, weatherDate);
        assertNotNull(output);

    }

}


