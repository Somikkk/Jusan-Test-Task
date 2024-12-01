package kz.islam.jusan.dto;

import lombok.Data;

@Data
public class WeatherForecastResponse {
    private Location location;
    private Forecast forecast;
}
