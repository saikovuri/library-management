package com.jpa.librarymanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rent_id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    private Date rentedDate;
    private Date dueDate;

    public Rental(int user_id, int book_id, Date rentedDate, Date dueDate) {
        this.user = new User();
        this.book = new Book();
        this.user.setUser_id(user_id);
        this.book.setBook_id(book_id);
        this.rentedDate = rentedDate;
        this.dueDate = dueDate;
        this.book.setAvailable(false);
    }

    public Rental(){

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getRent_id() {
        return rent_id;
    }

    public void setRent_id(int rent_id) {
        this.rent_id = rent_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Date getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(Date rentedDate) {
        this.rentedDate = rentedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


}
