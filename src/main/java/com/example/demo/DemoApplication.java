package com.example.demo;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class  DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppUserRepository appUserRepository) {
		return args -> {
			Faker faker = Faker.instance();
			int size = 20; // #appusers registered on the database

			appUserRepository.saveAll(generateRandomAppUsers(faker, size));
		};
	}

	private List<AppUser> generateRandomAppUsers(Faker faker, int size) {
		List<AppUser> appUsers = new ArrayList<>();
		String[] names = new String[2]; // First and last names

		for (int i = 0; i < size; i++) {
			names[0] = faker.name().firstName();
			names[1] = faker.name().lastName();

			appUsers.add(
					new AppUser(
						String.format("%s %s", names[0], names[1]),
						String.format("%s:%s", names[0].toLowerCase(), names[1].toLowerCase()),
						String.format("%s.%s@gmail.com", names[0].toLowerCase(), names[1].toLowerCase()),
						faker.internet().password(),
						null,
						true,
						false,
						true,
						false
					)
			);
		}
		return appUsers;
	}
}
