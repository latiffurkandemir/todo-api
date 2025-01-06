package com.todoapp.todo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ToDoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoApiApplication.class, args);
	}
}
