package kz.islam.jusan.config;

import kz.islam.jusan.dto.WeatherForecastResponse;
import kz.islam.jusan.dto.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherApi", url = "${feign.weather-api.url}")
public interface WeatherApiClient {

    @GetMapping("/current.json")
    WeatherResponse getCurrentWeather(
            @RequestParam("key") String apiKey,
            @RequestParam("q") String city);

    @GetMapping("/forecast.json")
    WeatherForecastResponse getForecast(
            @RequestParam("key") String apiKey,
            @RequestParam("q") String city,
            @RequestParam("days") int days);
}

