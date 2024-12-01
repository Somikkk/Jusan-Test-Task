package kz.islam.jusan.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kz.islam.jusan.models.City;
import kz.islam.jusan.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @Operation(
            summary = "Добавить новый город",
            description = "Создает новый город в базе данных",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Город успешно добавлен"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
            }
    )
    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody @Valid City city) {
        City createdCity = cityService.createCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
    }

    @Operation(
            summary = "Получить список всех городов",
            description = "Возвращает список всех городов",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Города найдены"),
                    @ApiResponse(responseCode = "404", description = "Города не найдены")
            }
    )
    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @Operation(
            summary = "Получить информацию о городе по ID",
            description = "Возвращает информацию о городе по его ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Город найден"),
                    @ApiResponse(responseCode = "404", description = "Город не найден")
            }
    )
    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @Operation(
            summary = "Обновить информацию о городе по ID",
            description = "Возвращает обновленную информацию о городе по его ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Город найден"),
                    @ApiResponse(responseCode = "404", description = "Город не найден")
            }
    )
    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody @Valid City city) {
        return cityService.updateCity(id, city);
    }

    @Operation(
            summary = "Удалить информацию о городе по ID",
            description = "Удаляет информацию о городе по его ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Город удален"),
                    @ApiResponse(responseCode = "404", description = "Город не найден")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}

