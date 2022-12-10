package com.github.{{ cookiecutter.user }}.{{ cookiecutter.application_name | replace('-', '') | lower }};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
