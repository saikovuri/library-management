package com.jpa.librarymanagement.controller;

import com.jpa.librarymanagement.model.Book;
import com.jpa.librarymanagement.model.User;
import com.jpa.librarymanagement.repo.BookRepo;
import com.jpa.librarymanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserRepo userRepo;

//    @GetMapping(value = "/user")
//    public String getUsers(Model model) {
//        model.addAttribute("users", userRepo.findAll());
//        return "user";
//    }
//
//
//    @GetMapping(value = "/user/{id}")
//    public Optional<User> getUserById(@PathVariable int id) {
//        return userRepo.findById(id);
//    }
}