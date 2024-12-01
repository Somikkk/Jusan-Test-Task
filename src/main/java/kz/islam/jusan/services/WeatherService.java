package kz.islam.jusan.services;

import feign.FeignException;
import kz.islam.jusan.config.WeatherApiClient;
import kz.islam.jusan.dto.WeatherForecastResponse;
import kz.islam.jusan.dto.WeatherResponse;
import kz.islam.jusan.exceptions.ApiUnavailableException;
import kz.islam.jusan.exceptions.AuthorizationException;
import kz.islam.jusan.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherApiClient weatherApiClient;

    @Value("${weatherapi.apiKey}")
    private String apiKey;

    public WeatherResponse getCurrentWeather(String cityName) {
        try {
            return weatherApiClient.getCurrentWeather(apiKey, cityName);
        } catch (FeignException e) {
            handleApiException(e);
            return null; // Никогда не вызовется из-за исключений выше.
        }
    }

    public WeatherForecastResponse getForecast(String cityName, int days) {
        try {
            return weatherApiClient.getForecast(apiKey, cityName, days);
        } catch (FeignException e) {
            handleApiException(e);
            return null; // Никогда не вызовется из-за исключений выше.
        }
    }

    private void handleApiException(FeignException e) {
        if (e.status() == 401) {
            throw new AuthorizationException("Ошибка авторизации: неверный API ключ");
        } else if (e.status() == 503) {
            throw new ApiUnavailableException("Сервис WeatherAPI временно недоступен");
        } else {
            throw new RuntimeException("Произошла ошибка при вызове WeatherAPI: " + e.getMessage());
        }
    }
}

