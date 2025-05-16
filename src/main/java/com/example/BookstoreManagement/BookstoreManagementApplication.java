package com.example.BookstoreManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookstoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreManagementApplication.class, args);
	}

}
