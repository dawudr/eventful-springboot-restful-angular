package com.dawud.events.service;

import com.dawud.events.domain.Event;
import com.dawud.events.domain.util.EventConstants;
import com.dawud.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for handling event request.
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEvents(String location, String category, String startdate, String enddate, String pageSize, String pageNumber) throws IOException {

        Map<String, String> params = new HashMap<>();
        String dateRange = "";
        if(!startdate.isEmpty()) {
            if(startdate.matches(".*[a-zA-Z]+.*")) {
                params.put(EventConstants.QUERY_PARAM_DATE, startdate);
            } else {
                if (enddate.isEmpty()) {
                    dateRange = startdate + "00";
                } else {
                    dateRange = startdate + "00-" + enddate + "00";
                }
                params.put(EventConstants.QUERY_PARAM_DATE, dateRange);
            }
        }
        if(!location.isEmpty()) {
            params.put(EventConstants.QUERY_PARAM_LOCATION, location);
        }
        if(!category.isEmpty()) {
            params.put(EventConstants.QUERY_PARAM_CATEGORY, category);
        }
        if(!pageSize.isEmpty()) {
            params.put(EventConstants.QUERY_PARAM_PAGESIZE, pageSize);
        }
        if(!pageNumber.isEmpty()) {
            params.put(EventConstants.QUERY_PARAM_PAGENUMBER, pageNumber);
        }

        List<Event> events = eventRepository.getEvents(params);

        return events;
    }

}
