package com.jpa.librarymanagement.controller;

import com.jpa.librarymanagement.model.Book;
import com.jpa.librarymanagement.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    BookRepo bookRepo;

//    @GetMapping(value = "/book")
//    public String getBooks(Model model) {
//       model.addAttribute("books", bookRepo.findAll());
//       return "book";
//    }

//    @GetMapping(value = "/book/{id}")
//    public String getBookById(Model model, @RequestParam(value="id", required=false, defaultValue="1") int id ) {
//        model.addAttribute("book", bookRepo.findById(id));
//        return "book";
//    }


//    @GetMapping(value = "/book/{id}", produces = "application/json")
//    public @ResponseBody Optional<Book> getBook(@PathVariable int id) {
//        return bookRepo.findById(id);
//    }
//



}
