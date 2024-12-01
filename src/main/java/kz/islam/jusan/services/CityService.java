package kz.islam.jusan.services;


import jakarta.persistence.EntityNotFoundException;
import kz.islam.jusan.models.City;
import kz.islam.jusan.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Город с ID " + id + " не найден"));
    }

    public City updateCity(Long id, City updatedCity) {
        City city = getCityById(id);
        city.setName(updatedCity.getName());
        city.setCountry(updatedCity.getCountry());
        return cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        City city = getCityById(id);
        cityRepository.delete(city);
    }
}

