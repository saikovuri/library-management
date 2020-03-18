package com.jpa.librarymanagement.model;

import java.util.Date;
import java.util.Objects;

public class CreateRequest {

    private int user_id;

    private int book_id;

    private Date rentDate;

    private Date dueDate;

    public CreateRequest(int user_id, int book_id, Date rentDate, Date dueDate) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
    }

    public CreateRequest(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "CreateRequest{" +
                "user_id=" + user_id +
                ", book_id=" + book_id +
                ", rentDate=" + rentDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
