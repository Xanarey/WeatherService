package net.xanarey.securitylearn.utils;

import net.xanarey.securitylearn.model.weather.ResponseWeather;
import net.xanarey.securitylearn.model.weather.Weather;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class WeatherFormatter {

    public static String format(ResponseWeather responseWeather) {
        StringBuilder sb = new StringBuilder();

        sb.append("Город: ").append(responseWeather.getName()).append(", ").append(responseWeather.getSys().getCountry()).append("\n\n");

        sb.append("Текущая погода:\n");
        sb.append("- Температура: ").append(responseWeather.getMain().getTemp()).append("K (примерно ").append(kelvinToCelsius(responseWeather.getMain().getTemp())).append("°C)\n");
        sb.append("- Ощущается как: ").append(responseWeather.getMain().getFeels_like()).append("K (примерно ").append(kelvinToCelsius(responseWeather.getMain().getFeels_like())).append("°C)\n");
        sb.append("- Мин. температура: ").append(responseWeather.getMain().getTemp_min()).append("K\n");
        sb.append("- Макс. температура: ").append(responseWeather.getMain().getTemp_max()).append("K\n");
        sb.append("- Влажность: ").append(responseWeather.getMain().getHumidity()).append("%\n");
        sb.append("- Давление: ").append(responseWeather.getMain().getPressure()).append(" hPa\n\n");

        sb.append("Погодные условия:\n");
        for (Weather weather : responseWeather.getWeather()) {
            sb.append("- ").append(weather.getDescription()).append("\n");
        }

        sb.append("\nВетер:\n");
        sb.append("- Скорость: ").append(responseWeather.getWind().getSpeed()).append(" m/s\n");
        sb.append("- Направление: ").append(responseWeather.getWind().getDeg()).append("°\n");
        sb.append("- Порывы: ").append(responseWeather.getWind().getGust()).append(" m/s\n\n");

        sb.append("Облачность: ").append(responseWeather.getClouds().getAll()).append("%\n\n");

        sb.append("Время восхода: ").append(convertTimestamp((int) responseWeather.getSys().getSunrise())).append("\n");
        sb.append("Время заката: ").append(convertTimestamp((int) responseWeather.getSys().getSunset())).append("\n");

        return sb.toString();
    }

    private static String kelvinToCelsius(double kelvin) {
        return String.format("%.2f", kelvin - 273.15);
    }

    private static String convertTimestamp(int timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}