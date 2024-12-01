package kz.islam.jusan.dto;

import lombok.Data;

@Data
public class WeatherResponse {
    private Location location;
    private Current current;
}
