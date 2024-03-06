package pl.morozgrusz.zycieklockow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.morozgrusz.zycieklockow.configs.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class ZycieklockowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZycieklockowApplication.class, args);
	}
}
