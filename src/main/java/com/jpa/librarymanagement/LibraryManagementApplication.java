package com.jpa.librarymanagement;

import com.jpa.librarymanagement.model.User;
import com.jpa.librarymanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class LibraryManagementApplication {
		//implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

//@Autowired
//UserRepo userRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//		userRepo.save(new User("Sai", 28, true, "saikovouri@gmail.com", "8602459741"));
//	}
}
