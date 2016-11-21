package com.dawud.events.repository;

import com.dawud.Application;
import com.dawud.events.domain.Event;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EventRepositoryTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void testGetEventsByFilter() throws Exception {
        Map params = new HashMap<>();
        params.put(EventConstants.QUERY_PARAM_LOCATION, "London");
        params.put(EventConstants.QUERY_PARAM_CATEGORY, "music");
        params.put(EventConstants.QUERY_PARAM_DATE, "Today");

        List<Event> output = eventRepository.getEvents(params);
        assertNotNull(output);
        assertTrue(output.size() > 0);
        assertNotNull(output.get(0).getId());
        assertNotNull(output.get(0).getTitle());
        assertNotNull(output.get(0).getStart_time());
        assertNotNull(output.get(0).getCity_name());
        assertEquals("City name should be London", output.get(0).getCity_name().equalsIgnoreCase("London"));
    }

    @Test
    public void testGetAllEvents() throws Exception {
        Map params = new HashMap<>();
        params.put(EventConstants.QUERY_PARAM_DATE, "Today");

        List<Event> output = eventRepository.getEvents(params);
        assertNotNull(output);
        assertTrue(output.size() > 0);
        assertNotNull(output.get(0).getId());
        assertNotNull(output.get(0).getTitle());
        assertNotNull(output.get(0).getStart_time());
        assertNotNull(output.get(0).getCity_name());
        boolean differentCitNames = false;
        for(Event ev : output) {
            if(!ev.getCity_name().equalsIgnoreCase(output.get(0).getCity_name())) {
                differentCitNames = true;
                break;
            }
        }
        assertTrue("City names are different", differentCitNames);
    }


    // Getters and Setters

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}


