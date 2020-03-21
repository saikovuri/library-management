package com.jpa.librarymanagement.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rent_id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    private LocalDate rentedDate;
    private LocalDate dueDate;

    public Rental(int user_id, int book_id, LocalDate rentedDate, LocalDate dueDate) {
        this.user = new User();
        this.book = new Book();
        this.user.setUser_id(user_id);
        this.book.setBook_id(book_id);
        this.rentedDate = rentedDate;
        this.dueDate = dueDate;
    }

    public Rental(){

    }

    public int getRent_id() {
        return rent_id;
    }

    public void setRent_id(int rent_id) {
        this.rent_id = rent_id;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


}
