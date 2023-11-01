package net.xanarey.securitylearn.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@Slf4j
@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";
    private final RestTemplate restTemplate;
    @Getter
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final ScheduledExecutorService scheduledExecutorService;

    @Autowired
    public WeatherService(RestTemplate restTemplate, ScheduledExecutorService scheduledExecutorService) {
        this.restTemplate = restTemplate;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @PostConstruct
    public void startWeatherUpdates() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            getWeatherByCity("Moscow");
        }, 0, 1, TimeUnit.MINUTES);
    }

    public CompletableFuture<String> getWeatherByCity(String city) {
        log.info("--------------------------------------------------------");
        log.info("Request thread: " + Thread.currentThread().getName());
        log.info("Handling weather request for city: {}", city);
        return CompletableFuture.supplyAsync(() -> {
            try {
                String response = restTemplate.getForObject(WEATHER_URL, String.class, city, apiKey);
                log.info("Weather data received successfully for city: {}", city);
                log.info("Async thread: " + Thread.currentThread().getName());
                log.info("--------------------------------------------------------");
                return response;
            } catch (Exception e) {
                log.error("Error handling weather request for city: {}", city, e);
                log.info("--------------------------------------------------------");
                throw e;
            }
        }, executorService);
    }

    @PreDestroy
    public void destroy() { executorService.shutdown(); }

}