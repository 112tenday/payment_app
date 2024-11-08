package api.nutech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "api.nutech")
public class NutechApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutechApplication.class, args);
	}

}
