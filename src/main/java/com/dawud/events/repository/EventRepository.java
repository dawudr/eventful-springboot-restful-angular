package com.dawud.events.repository;

import com.dawud.config.EventfulRestClient;
import com.dawud.events.domain.Event;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.net.URLEncoder.encode;

@Component
public class EventRepository {

    @Value("${app.eventful.api.endpoint.search}")
    private String searchRestEndpointUrl;

    @Autowired
    private EventfulRestClient restClient;

    public List<Event> getEvents(Map filter) throws IOException {
        String filterString = getURLFilterString(filter);
        List<Event> events = getEvents(filterString);
        return events;
    }


    private String getURLFilterString(Map filter) {
        StringBuilder filterParams = new StringBuilder();
        if (!filter.isEmpty() && filter.size() > 0) {
            filter.forEach((key, value) -> {
                try {
                    if (!value.toString().isEmpty()) {
                        filterParams.append("&" + key + "=" + encode(value.toString(), "UTF-8"));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
        }
        return filterParams.toString();
    }

    @Retryable(maxAttempts = 3, value = RuntimeException.class, backoff = @Backoff(delay = 2000, multiplier = 2))
    private List<Event> getEvents(String queryString) throws IOException {
        String endPointUrl = searchRestEndpointUrl + queryString;
        System.out.println(endPointUrl);

        try {
            ResponseEntity<String> response = restClient.restTemplate().getForEntity(endPointUrl, String.class);

            switch (response.getStatusCode().value()) {
                case 200:

                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    JsonNode root = mapper.readTree(response.getBody());

                    List<Event> events = null;
                    JsonNode eventsNode = root.get("events");
                    if (eventsNode != null && !eventsNode.asText().equals("null") && eventsNode.has("event")) {
                        Event[] eventArray = mapper.readValue(eventsNode.get("event").toString(), Event[].class);
                        events = Arrays.asList(eventArray);
                    }
                    return events;

                case 503:
                    throw new RuntimeException("Server Response: " + response.getStatusCode());
                default:
                    throw new IllegalStateException("Server not ready");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}


