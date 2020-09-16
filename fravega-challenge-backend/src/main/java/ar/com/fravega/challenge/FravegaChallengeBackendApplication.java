package ar.com.fravega.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ar.com.fravega.challenge"})
public class FravegaChallengeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FravegaChallengeBackendApplication.class, args);
	}

}
