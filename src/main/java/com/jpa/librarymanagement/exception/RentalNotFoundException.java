package com.jpa.librarymanagement.exception;

public class RentalNotFoundException extends RuntimeException {

    public RentalNotFoundException(int id){
        super("Rental not found : " + id);
    }

}
