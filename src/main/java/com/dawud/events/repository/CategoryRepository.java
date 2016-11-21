package com.dawud.events.repository;

import com.dawud.config.EventfulRestClient;
import com.dawud.events.domain.Category;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestOperations;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CategoryRepository {

    @Value("${app.eventful.api.endpoint.categories}")
    private String categoriesRestEndpointUrl;

    @Autowired
    private EventfulRestClient restClient;

    @Retryable(maxAttempts=3,value=RuntimeException.class,backoff = @Backoff(delay = 1000,multiplier=2))
    public List<Category> getCategories() throws IOException {

/*
        // Tests were failing as the problem is the Eventful API JSON has Content-type →text/javascript; charset=utf-8
        and cannot be deserialized into a Collection because it's not actually a JSON Array i.e
        {
          "category": [
            {
              "name": "Concerts &amp; Tour Dates",
              "event_count": null,
              "id": "music"
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        // Add the Jackson message converter
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<HttpMessageConverter<?>> msgConverters = restTemplate.getMessageConverters();
        msgConverters.add(jsonConverter);
        restTemplate.setMessageConverters(msgConverters);

        // Extract Categories as list
        ResponseEntity<List<Category>> response = restTemplate.exchange(categoriesRestEndpointUrl,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Category>>() {
                });
        List<Category> categories = response.getBody();
        categories.forEach(s -> System.out.println(s));
*/


        try {
            // Take control of the deserialization process
            ResponseEntity<String> response = restClient.restTemplate().getForEntity(categoriesRestEndpointUrl, String.class);

            switch (response.getStatusCode().value()) {
                case 200:
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(response.getBody());
                    Category[] categoryArray = mapper.readValue(root.get("category").toString(), Category[].class);
                    List<Category> categories = Arrays.asList(categoryArray);
                    return categories;
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


