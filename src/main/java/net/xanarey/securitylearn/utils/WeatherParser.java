package net.xanarey.securitylearn.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.xanarey.securitylearn.model.weather.ResponseWeather;

import java.io.IOException;

public class WeatherParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ResponseWeather parse(String json) throws IOException {
        return objectMapper.readValue(json, ResponseWeather.class);
    }
}