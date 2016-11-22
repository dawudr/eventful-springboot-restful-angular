package com.dawud.weather.controllers;

import com.dawud.events.domain.Event;
import com.dawud.events.domain.util.EventConstants;
import com.dawud.events.service.EventService;
import com.dawud.weather.domain.EventWeather;
import com.dawud.weather.domain.Weather;
import com.dawud.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EventWeatherController {

    private final EventService eventService;
    private final WeatherService weatherService;

    @Autowired
    EventWeatherController(EventService eventService, WeatherService weatherService) {
        this.eventService = eventService;
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/eventsandweather", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventWeather> getEventsAndWeather(@RequestParam(value = EventConstants.QUERY_PARAM_LOCATION, required=false, defaultValue="") String location,
                                               @RequestParam(value = EventConstants.QUERY_PARAM_CATEGORY, required=false, defaultValue="") String category,
                                               @RequestParam(value = EventConstants.QUERY_PARAM_STARTDATE, required=false, defaultValue="Today") String startdate,
                                               @RequestParam(value = EventConstants.QUERY_PARAM_ENDDATE, required=false, defaultValue="") String enddate,
                                               @RequestParam(value = EventConstants.QUERY_PARAM_PAGESIZE, required=false, defaultValue="10") String pagesize,
                                               @RequestParam(value = EventConstants.QUERY_PARAM_PAGENUMBER, required=false, defaultValue="1") String pagenumber) throws IOException {
        List<Event> events = eventService.getEvents(location, category, startdate, enddate, pagesize, pagenumber);
        List<EventWeather> eventWeathers = new ArrayList<>();
        if (events != null && events.size() > 0) {
            for (Event event : events) {
                Weather weather = weatherService.getWeather(event.getLatitude(), event.getLongitude(), event.getStart_time());
                EventWeather eventWeather = new EventWeather(event, weather);
                eventWeathers.add(eventWeather);
            }
        }
        return eventWeathers;
    }

}
