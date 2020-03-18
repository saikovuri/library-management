package com.jpa.librarymanagement.controller;

import com.jpa.librarymanagement.model.Book;
import com.jpa.librarymanagement.model.CreateRequest;
import com.jpa.librarymanagement.model.Rental;
import com.jpa.librarymanagement.model.User;
import com.jpa.librarymanagement.repo.BookRepo;
import com.jpa.librarymanagement.repo.RentalRepo;
import com.jpa.librarymanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class GenericController {
    @Autowired
    BookRepo bookRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RentalRepo rentalRepo;

    Rental rental;
//    @GetMapping(value = "/book/{id}", produces = "application/json")
//    public Optional<Book> getBook(@PathVariable int id) {
//        return bookRepo.findById(id);
//    }

//    @PostMapping(value = "/rent/{userId}/{bookId}")
//    public String rentABook(@PathVariable int userId, @PathVariable int bookId){
//        Date date = new Date();
//       rental = new Rental(userId,bookId,date,date);
//       rentalRepo.rentBook(rental);
//         return "Success ";
//    }


    @PostMapping(value = "/book")
    public void insertBook(@RequestBody Book book) {
        Book bookCur = new Book(book.getBookName(),book.getAuthorName(), book.getPrice());
        bookRepo.save(bookCur);
    }

    @PostMapping(value = "/user")
    public void insertUser(@RequestBody User user) {
        User userCur = new User(user.getUserName(),user.getAge(),user.isActive(),user.getEmail(),user.getMobileNumber());
        userRepo.save(userCur);
    }

    @Transactional
    @PostMapping(value = "/rental")
    public void insertRental(@RequestBody CreateRequest rental) {
        Rental rentalCur = new Rental(rental.getUser_id(),rental.getBook_id(), new Date(), new Date());
        rentalRepo.save(rentalCur);
        bookRepo.updateBookStatusToAvailable(rental.getBook_id(),false);
    }

    @GetMapping(value = "/availableBooks")
    public List<Book> getAvailableBooks(){
        List<Book> list = bookRepo.getAllAvailableBook();
        return list;
    }



}
