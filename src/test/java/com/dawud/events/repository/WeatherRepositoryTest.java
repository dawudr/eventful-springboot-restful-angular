package com.dawud.events.repository;

import com.dawud.Application;
import com.dawud.events.domain.Event;
import com.dawud.events.domain.Weather;
import com.dawud.events.domain.util.EventConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


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


