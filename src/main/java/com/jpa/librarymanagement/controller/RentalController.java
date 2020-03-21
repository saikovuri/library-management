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
    public ResponseEntity<Rental> getUserById(@PathVariable int id) {
        Rental rental = rentalRepo.getRentalById(id);
        if (rental == null)
            return null;
        return new ResponseEntity(rental, HttpStatus.OK);
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
        Rental rental = null;

        try {
            rental = rentalRepo.getRentalById(rentId);
            if (rental == null) {
                throw new RentalNotFoundException(5);
            }
        } catch (RentalNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Rental not Found", ex);
        }
        bookRepo.updateBookStatusToAvailable(rental.getBook().getBook_id(), true);
        rentalRepo.deleteById(rentId);
        return ResponseEntity.ok().build();
    }


}
