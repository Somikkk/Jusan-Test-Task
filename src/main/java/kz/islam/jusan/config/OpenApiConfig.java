package kz.islam.jusan.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Jusan Test Task Application")
                        .version("1.0.0")
                        .description("Документация для тестирования API")
                        .contact(new Contact()
                                .name("Ислам")
                                .email("somnus_m@mail.ru")));
    }
}
