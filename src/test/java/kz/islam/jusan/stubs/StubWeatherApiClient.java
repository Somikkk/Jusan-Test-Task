package kz.islam.jusan.stubs;

import kz.islam.jusan.config.WeatherApiClient;
import kz.islam.jusan.dto.*;

import java.util.ArrayList;
import java.util.List;

public class StubWeatherApiClient implements WeatherApiClient {
    @Override
    public WeatherResponse getCurrentWeather(String apiKey, String city) {
        WeatherResponse response = new WeatherResponse();
        Location location = new Location();
        location.setName(city);
        location.setRegion("Almaty City");
        location.setCountry("Kazakhstan");
        response.setLocation(location);
        return response;
    }

    @Override
    public WeatherForecastResponse getForecast(String apiKey, String city, int days) {
        WeatherForecastResponse response = new WeatherForecastResponse();

        Location location = new Location();
        location.setName(city);
        location.setRegion("SomeRegion");
        location.setCountry("SomeCountry");
        response.setLocation(location);

        Forecast forecast = new Forecast();
        List<ForecastDay> forecastDays = new ArrayList<>();

        for (int i = 1; i <= days; i++) {
            ForecastDay forecastDay = new ForecastDay();
            forecastDay.setDate("2024-12-" + (10 + i)); // Пример даты

            Day day = new Day();
            day.setMaxTempC(10 + i); // Пример температуры
            day.setMinTempC(5 + i);  // Пример температуры

            Condition condition = new Condition();
            condition.setText("Sunny");
            condition.setIcon("sunny-icon.png");
            day.setCondition(condition);

            forecastDay.setDay(day);
            forecastDays.add(forecastDay);
        }

        forecast.setForecastDay(forecastDays);
        response.setForecast(forecast);

        return response;
    }
}
