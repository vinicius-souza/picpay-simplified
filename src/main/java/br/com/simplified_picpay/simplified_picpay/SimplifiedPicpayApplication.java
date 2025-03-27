package br.com.simplified_picpay.simplified_picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimplifiedPicpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplifiedPicpayApplication.class, args);
	}

}
