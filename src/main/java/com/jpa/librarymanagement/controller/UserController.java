package com.jpa.librarymanagement.controller;

import com.jpa.librarymanagement.LibraryManagementApplication;
import com.jpa.librarymanagement.exception.BookNotFoundException;
import com.jpa.librarymanagement.exception.UserNotFoundException;
import com.jpa.librarymanagement.model.Book;
import com.jpa.librarymanagement.model.BookList;
import com.jpa.librarymanagement.model.User;
import com.jpa.librarymanagement.repo.UserRepo;
import com.jpa.librarymanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    Util util;

    @GetMapping(value = "/user")
    public List<User> getUsers() {

        List<User> list = null;
        list = userRepo.findAll();
        if (list.size() == 0 || list == null)
            return Collections.emptyList();

        return list;

    }

    @GetMapping(value = "/user/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        Optional<User> user = userRepo.findById(id);
        if (user == null)
            return null;
        return user;
    }

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User insertUser(@RequestBody User user) {
        if (user.getUserName() == "" || user.getUserName() == null)
            return null;
        return userRepo.save(user);
    }

//    @GetMapping(value = "/searchRentedBookByUser")
//    public List<Object> searchRentedByUser(@RequestParam(value = "q") int userId) {
//        List<Object> bookList = null;
//        bookList = userRepo.getRentalsByUser(userId);
//        if (bookList == null || bookList.size() == 0)
//            return Collections.emptyList();
//        return bookList;
//    }


    @GetMapping(value = "/searchRentedBookByUser")
    public List<BookList> searchRentedByUser(@RequestParam(value = "q") int userId) {
        List<BookList> bookList = null;
        bookList = util.getRentalsByUser(entityManager,userId);
        if (bookList == null || bookList.size() == 0)
            return Collections.emptyList();
        return bookList;
    }

    @DeleteMapping(value = "/user")
    public ResponseEntity deleteUser(@RequestParam(value = "q") int userId) {
        User user = null;
        try {
            user = userRepo.getUserById(userId);
            if (user == null) {
                throw new UserNotFoundException(5);
            }
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not Found", ex);
        }
        userRepo.deleteById(userId);
        return ResponseEntity.ok().build();
    }

}