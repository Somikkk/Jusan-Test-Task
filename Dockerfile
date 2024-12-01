# Используем базовый образ OpenJDK
FROM openjdk:17-jdk-slim

# Указываем рабочую директорию
WORKDIR /app

# Копируем JAR файл микросервиса
COPY target/jusan-0.0.1-SNAPSHOT.jar weather-microservice.jar

# Указываем команду запуска
ENTRYPOINT ["java", "-jar", "weather-microservice.jar"]

# Открываем порт 8080
EXPOSE 8080
