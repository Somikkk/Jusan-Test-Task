package kz.islam.jusan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JusanApplication {

	public static void main(String[] args) {
		SpringApplication.run(JusanApplication.class, args);
	}

}
