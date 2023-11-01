package net.xanarey.securitylearn.controller;

import lombok.extern.slf4j.Slf4j;
import net.xanarey.securitylearn.model.weather.ResponseWeather;
import net.xanarey.securitylearn.service.WeatherService;
import net.xanarey.securitylearn.utils.WeatherFormatter;
import net.xanarey.securitylearn.utils.WeatherParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    @PreAuthorize("hasAuthority('dev:admin')")
    public CompletableFuture<ResponseEntity<String>> getWeather(@PathVariable String city) {
        return weatherService.getWeatherByCity(city)
                .thenApplyAsync(weatherData -> {
                    try {
                        ResponseWeather responseWeather = WeatherParser.parse(weatherData);
                        String result = WeatherFormatter.format(responseWeather);
                        return new ResponseEntity<>(result, HttpStatus.OK);
                    } catch (IOException e) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error parsing weather data");
                    }
                }, weatherService.getExecutorService())
                .exceptionally(e -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}