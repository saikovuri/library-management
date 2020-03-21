package com.jpa.librarymanagement.controller;

import com.jpa.librarymanagement.exception.RentalNotFoundException;
import com.jpa.librarymanagement.model.*;
import com.jpa.librarymanagement.repo.BookRepo;
import com.jpa.librarymanagement.repo.RentalRepo;
import com.jpa.librarymanagement.repo.UserRepo;
import com.jpa.librarymanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class RentalController {
    @Autowired
    BookRepo bookRepo;

    @Autowired
    RentalRepo rentalRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    Util util;


    @GetMapping("/rentedBooks")
    List<Rental> findAll() {
        return rentalRepo.findAll();
    }

    @GetMapping(value = "/rental/{id}")
    public Optional<Rental> getUserById(@PathVariable int id) {
        Optional<Rental> rental = rentalRepo.findById(id);
        if (rental == null)
            return null;
        return rental;
    }


    @Transactional
    @PostMapping(value = "/rental")
    @ResponseStatus(HttpStatus.CREATED)
    public String insertRental(@RequestBody CreateRequest rental) {
        boolean available = bookRepo.isAvailable(rental.getBook_id());
        if (!available)
            return "Book is not available";
        bookRepo.updateBookStatusToNotAvailable(rental.getBook_id(), false);
        Rental rentalCur = new Rental(rental.getUser_id(), rental.getBook_id(), LocalDate.now(), LocalDate.now().plusDays(5));
        rentalRepo.save(rentalCur);
        return "Success";

    }

    @Transactional
    @DeleteMapping(value = "/rental")
    public ResponseEntity deleteRental(@RequestParam(value = "q") int rentId) {
        Optional<Rental> rental = null;

        try {
            rental = rentalRepo.findById(rentId);
            if (rental.equals(Optional.empty())) {
                throw new RentalNotFoundException(5);
            }
        } catch (RentalNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Rental not Found", exc);
        }
        bookRepo.updateBookStatusToAvailable(util.getBookIdbyRental(entityManager, rentId), true);
        rentalRepo.deleteById(rentId);
        return ResponseEntity.ok().build();
    }


}
