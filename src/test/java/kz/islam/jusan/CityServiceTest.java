package kz.islam.jusan;


import jakarta.persistence.EntityNotFoundException;
import kz.islam.jusan.models.City;
import kz.islam.jusan.repositories.CityRepository;
import kz.islam.jusan.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCityAndReturnCreatedCity() {
        City city = new City("Aktau", "Kazakhstan");
        when(cityRepository.save(city)).thenReturn(city);

        City result = cityService.createCity(city);

        assertNotNull(result);
        assertEquals("Aktau", result.getName());
        assertEquals("Kazakhstan", result.getCountry());
        verify(cityRepository, times(1)).save(city);
    }

    @Test
    void getAllCitiesAndReturnListOfCities() {
        City city1 = new City("Almaty", "Kazakhstan");
        City city2 = new City("Petropavlovsk", "Kazakhstan");
        when(cityRepository.findAll()).thenReturn(Arrays.asList(city1, city2));

        List<City> result = cityService.getAllCities();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(cityRepository, times(1)).findAll();
    }

    @Test
    void getCityByIdAndReturnCityWhenCityExists() {
        City city = new City("Almaty", "Kazakhstan");
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        City result = cityService.getCityById(1L);

        assertNotNull(result);
        assertEquals("Almaty", result.getName());
        verify(cityRepository, times(1)).findById(1L);
    }

    @Test
    void getCityByIdAndThrowExceptionWhenCityDoesNotExist() {
        when(cityRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> cityService.getCityById(1L));

        assertEquals("Город с ID 1 не найден", exception.getMessage());
        verify(cityRepository, times(1)).findById(1L);
    }

    @Test
    void updateCityAndReturnUpdatedCity() {
        City existingCity = new City("Almaty", "Kazakhstan");
        City updatedCity = new City("Astana", "Kazakhstan");
        when(cityRepository.findById(1L)).thenReturn(Optional.of(existingCity));
        when(cityRepository.save(existingCity)).thenReturn(updatedCity);

        City result = cityService.updateCity(1L, updatedCity);

        assertNotNull(result);
        assertEquals("Astana", result.getName());
        verify(cityRepository, times(1)).findById(1L);
        verify(cityRepository, times(1)).save(existingCity);
    }

    @Test
    void deleteCityWhenCityExists() {
        City city = new City("Almaty", "Kazakhstan");
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        assertDoesNotThrow(() -> cityService.deleteCity(1L));

        verify(cityRepository, times(1)).findById(1L);
        verify(cityRepository, times(1)).delete(city);
    }
}
