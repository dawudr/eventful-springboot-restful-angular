package com.dawud.weather.domain;

import com.dawud.events.domain.Event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventWeather extends Event implements Serializable {
    private Weather weather;

    public EventWeather(Event event, Weather weather) {
        super(
            event.getId(),
            event.getTitle(),
            event.getUrl(),
            event.getDescription(),
            event.getStart_time(),
            event.getStop_time(),
            event.getVenue_name(),
            event.getVenue_display(),
            event.getVenue_address(),
            event.getCity_name(),
            event.getRegion_name(),
            event.getRegion_abbr(),
            event.getPostal_code(),
            event.getCountry_name(),
            event.getCounty_abbr2(),
            event.getCountry_abbr(),
            event.getLatitude(),
            event.getLongitude()
        );
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

}
