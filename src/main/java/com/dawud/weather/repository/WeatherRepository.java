package com.dawud.weather.repository;

import com.dawud.config.EventfulRestClient;
import com.dawud.weather.domain.Weather;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WeatherRepository {

    @Value("${app.openweathermap.api.endpoint}")
    private String weatherRestEndpointUrl;
    private static final String QUERY_PARAM_LONGITUDE = "lon";
    private static final String QUERY_PARAM_LATITUDE = "lat";

    @Autowired
    private EventfulRestClient restClient;

    @Retryable(maxAttempts = 3, value = RuntimeException.class, backoff = @Backoff(delay = 2000, multiplier = 2))
    public Weather getWeather(Double latitude, Double longitude, Date date) throws IOException {
        String endPointUrl = weatherRestEndpointUrl + "&" + QUERY_PARAM_LATITUDE + "=" + latitude + "&" + QUERY_PARAM_LONGITUDE + "=" + longitude;
        System.out.println(endPointUrl);

        try {
            ResponseEntity<String> response = restClient.restTemplate().getForEntity(endPointUrl, String.class);

            switch (response.getStatusCode().value()) {
                case 200:

                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(response.getBody());

                    Weather weather = new Weather();
                    JsonNode weathersNode = root.get("list");
                    if (weathersNode != null && !weathersNode.asText().equals("null")) {

                        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date weatherDate = parser.parse(weathersNode.get(0).get("dt_txt").asText());

                        weather.setDt_txt(weatherDate);
                        JsonNode main = weathersNode.get(0).get("main");
                        weather.setTemp(main.get("temp").asText());

                        JsonNode weatherDescriptonNode = weathersNode.get(0).get("weather");
                        weather.setDescription(weatherDescriptonNode.get(0).get("description").asText());

                    }
                    return weather;

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