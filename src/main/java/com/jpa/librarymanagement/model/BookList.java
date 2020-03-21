package com.jpa.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BookList implements Serializable {

    @JsonProperty("book_id")
    private int book_id;
    @JsonProperty("book_name")
    private String book_name;
    @JsonProperty("author_name")
    private String author_name;

    public BookList() {
    }

    public BookList(Object[] columns) {
        this.book_id =  (Integer) columns[0];
        this.book_name = (String) columns[1];
        this.author_name = (String) columns[2];
    }

    public BookList(int book_id, String book_name, String author_name) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.author_name = author_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

}

