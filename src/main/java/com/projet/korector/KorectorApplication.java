package com.projet.korector;

import com.projet.korector.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class KorectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(KorectorApplication.class, args);
	}

}
