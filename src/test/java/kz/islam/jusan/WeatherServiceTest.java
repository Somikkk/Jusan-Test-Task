package kz.islam.jusan;

import kz.islam.jusan.config.WeatherApiClient;
import kz.islam.jusan.dto.ForecastDay;
import kz.islam.jusan.dto.Location;
import kz.islam.jusan.dto.WeatherForecastResponse;
import kz.islam.jusan.dto.WeatherResponse;
import kz.islam.jusan.services.WeatherService;
import kz.islam.jusan.stubs.StubWeatherApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    @Test
    void getCurrentWeather_ShouldReturnWeatherResponse() {

        String cityName = "Almaty";


        WeatherApiClient stubClient = new StubWeatherApiClient();
        WeatherService weatherService = new WeatherService(stubClient);


        WeatherResponse result = weatherService.getCurrentWeather(cityName);

        // Проверка результата
        assertNotNull(result, "Результат вызова не должен быть null");
        assertNotNull(result.getLocation(), "Локация не должна быть null");
        assertEquals("Almaty", result.getLocation().getName());
        assertEquals("Almaty City", result.getLocation().getRegion());
        assertEquals("Kazakhstan", result.getLocation().getCountry());
    }

    @Test
    void getForecast_ShouldReturnForecastResponse_WhenApiCallIsSuccessful() {
        String apiKey = "dummyApiKey";
        String cityName = "Almaty";
        int days = 3;

        WeatherApiClient stubClient = new StubWeatherApiClient();
        WeatherService weatherService = new WeatherService(stubClient);

        // Выполнение метода
        WeatherForecastResponse result = weatherService.getForecast(cityName, days);

        // Проверка результата
        assertNotNull(result, "Результат вызова не должен быть null");
        assertNotNull(result.getForecast(), "Прогноз не должен быть null");
        assertEquals(days, result.getForecast().getForecastDay().size(), "Количество дней прогноза не совпадает");

        for (int i = 0; i < days; i++) {
            ForecastDay forecastDay = result.getForecast().getForecastDay().get(i);
            assertNotNull(forecastDay.getDate(), "Дата не должна быть null");
            assertNotNull(forecastDay.getDay(), "День не должен быть null");
            assertEquals("Sunny", forecastDay.getDay().getCondition().getText());
        }
    }

}


