package kz.islam.jusan.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kz.islam.jusan.dto.WeatherForecastResponse;
import kz.islam.jusan.dto.WeatherResponse;
import kz.islam.jusan.models.City;
import kz.islam.jusan.services.CityService;
import kz.islam.jusan.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    private final CityService cityService;


    @Operation(
            summary = "Получить информацию о погоде по ID города",
            description = "Возвращает информацию о городе по его ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Город найден, выводится погода"),
                    @ApiResponse(responseCode = "404", description = "Город не найден")
            }
    )
    @GetMapping("/current/{cityId}")
    public WeatherResponse getCurrentWeather(@PathVariable Long cityId) {
        City city = cityService.getCityById(cityId);
        return weatherService.getCurrentWeather(city.getName());
    }


    @Operation(
            summary = "Получить информацию о погоде по ID города на несколько дней",
            description = "Возвращает информацию о погоде в городе по ID города",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Город найден, выводится погода на несколько дней"),
                    @ApiResponse(responseCode = "404", description = "Город не найден")
            }
    )
    @GetMapping("/forecast/{cityId}")
    public WeatherForecastResponse getWeatherForecast(@PathVariable Long cityId,
                                                      @RequestParam(defaultValue = "3") int days) {
        City city = cityService.getCityById(cityId);
        return weatherService.getForecast(city.getName(), days);
    }
}
