package com.jpa.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BookId implements Serializable {

    @JsonProperty("book_id")
    private int book_id;

    @JsonProperty("user_id")
    private int user_id;

    public BookId(Object[] columns) {
        this.book_id =  (Integer) columns[0];
        this.user_id =  (Integer) columns[1];
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
}
