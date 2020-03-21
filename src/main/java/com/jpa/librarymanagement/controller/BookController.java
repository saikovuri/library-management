package com.jpa.librarymanagement.controller;

import com.jpa.librarymanagement.exception.BookNotFoundException;
import com.jpa.librarymanagement.model.Book;
import com.jpa.librarymanagement.model.Rental;
import com.jpa.librarymanagement.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepo bookRepo;

    @GetMapping(value = "/book")
    public List<Book> getBooks() {
        List<Book> list = null;
        list = bookRepo.findAll();
        if (list.size() == 0 || list == null)
            return Collections.emptyList();

        return list;
    }

    @GetMapping(value = "/book/{id}", produces = "application/json")
    public @ResponseBody
    Optional<Book> getBookById(@PathVariable int id) {

        Optional<Book> book = bookRepo.findById(id);
        if (book == null)
            return null;
        return book;

    }


    @PostMapping(value = "/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book insertBook(@RequestBody Book book) {
        if (book.getBookName() == "" || book.getBookName().equals(null))
            return null;
        Book bookCur = new Book(book.getBookName(), book.getAuthorName(), book.getPrice());
        return bookRepo.save(bookCur);
    }

    @GetMapping(value = "/availableBooks")
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = bookRepo.getAllAvailableBook();
        if (availableBooks == null || availableBooks.size() == 0)
            return null;
        return availableBooks;
    }

    @GetMapping(value = "/availableBook")
    public boolean isBookAvailable(@RequestParam(value = "q") int book_id) {
        return bookRepo.isAvailable(book_id);
    }


    @GetMapping(value = "/booksByAuthor")
    public List<Book> searchBooksByAuthor(@RequestParam(value = "q") String author) {
        List<Book> books = bookRepo.getAllBookByAuthorName(author);
        if (books == null || books.size() == 0)
            return null;
        return books;
    }

    @GetMapping(value = "/bookByName")
    public Book searchBooksByName(@RequestParam(value = "q") String book_name) {
        Book book = bookRepo.getBookByName(book_name);
        if (book == null)
            return null;
        return book;
    }

    @DeleteMapping(value = "/book")
    public ResponseEntity deleteBook(@RequestParam(value = "q") int bookId) {
        Book book = null;
        try {
            book = bookRepo.getBookById(bookId);
            if (book == null) {
                throw new BookNotFoundException(5);
            }
        } catch (BookNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book not Found", ex);
        }
        bookRepo.deleteById(bookId);
        return ResponseEntity.ok().build();
    }

}
