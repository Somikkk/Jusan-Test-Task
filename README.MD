# Weather Api Task
## Требования

- Java 17
- Docker
- Maven

# Сборка проекта
Выполните команду ```mvn clean install```

Затем выполните команду ```mvn clean package```

# Запуск в Docker
Выполните команду ```docker compose up --build```



## Примеры запросов в Postman
### Cities API
POST http://localhost:8080/api/cities

Content-Type: application/json
```json
{
"name": "Almaty",
"country": "Kazakhstan"
}
```

GET http://localhost:8080/api/cities/1

Content-Type: application/json
```json
{
  "name": "Almaty"
}
```

PUT http://localhost:8080/api/cities/1

Content-Type: application/json
```json
{
  "name": "Akatu"
}
```

DELETE http://localhost:8080/api/cities/1

### Weather API

GET http://localhost:8080/api/weather/current/{cityId}

```json
{
    "location": {
        "name": "Almaty",
        "region": "Almaty City",
        "country": "Kazakhstan",
        "lat": 43.25,
        "lon": 76.95,
        "tz_id": "Asia/Almaty",
        "localtime_epoch": 1733052090,
        "localtime": "2024-12-01 16:21"
    },
    "current": {
        "last_updated_epoch": 1733051700,
        "last_updated": "2024-12-01 16:15",
        "temp_c": 2.2,
        "temp_f": 36.0,
        "is_day": 0,
        "condition": {
            "text": "Light snow showers",
            "icon": "//cdn.weatherapi.com/weather/64x64/night/368.png",
            "code": 1255
        },
        "wind_mph": 2.2,
        "wind_kph": 3.6,
        "wind_degree": 301,
        "wind_dir": "WNW",
        "pressure_mb": 1026.0,
        "pressure_in": 30.3,
        "precip_mm": 1.11,
        "precip_in": 0.04,
        "humidity": 87,
        "cloud": 100,
        "feelslike_c": 1.6,
        "feelslike_f": 34.9,
        "windchill_c": 2.0,
        "windchill_f": 35.5,
        "heatindex_c": 2.0,
        "heatindex_f": 35.5,
        "dewpoint_c": 1.8,
        "dewpoint_f": 35.3,
        "vis_km": 5.0,
        "vis_miles": 3.0,
        "uv": 0.0,
        "gust_mph": 6.7,
        "gust_kph": 10.8
    }
}
```

GET http://localhost:8080/api/weather/forecast/{cityId}?days=3

