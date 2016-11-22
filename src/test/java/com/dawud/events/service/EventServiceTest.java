package com.dawud.events.service;

import com.dawud.Application;
import com.dawud.events.domain.Event;
import com.dawud.events.domain.util.EventConstants;
import com.dawud.events.repository.EventRepository;
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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testGetEventsByCategory() throws Exception {
        try {

            List<Event> events= eventService.getEvents("", "music", "Today", "", "", "");
            assertNotNull(events);
            assertTrue(events.size() > 0);
            assertNotNull(events.get(0).getId());
            assertNotNull(events.get(0).getTitle());
            assertNotNull(events.get(0).getStart_time());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetEventsByLocation() throws Exception {
        try {

            List<Event> events= eventService.getEvents("London", "", "Today", "", "", "");
            assertNotNull(events);
            assertTrue(events.size() > 0);
            assertNotNull(events.get(0).getId());
            assertNotNull(events.get(0).getTitle());
            assertNotNull(events.get(0).getStart_time());
            events.forEach(s -> assertEquals("City name should be London", s.getCity_name().toLowerCase(), "london"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetEvents() throws Exception {
        try {

            List<Event> events= eventService.getEvents("London", "music", "Today", "", "", "");
            assertNotNull(events);
            assertTrue(events.size() > 0);
            assertNotNull(events.get(0).getId());
            assertNotNull(events.get(0).getTitle());
            assertNotNull(events.get(0).getStart_time());
            events.forEach(s -> assertEquals("City name should be London", "London", s.getCity_name()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


