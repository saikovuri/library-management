package com.jpa.librarymanagement.model;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    private String bookName;
    private String authorName;
    private double price;
    private boolean isAvailable;
    @OneToOne(mappedBy="book",
            cascade= {CascadeType.ALL},
            fetch=FetchType.LAZY)
    private Rental rental;

    public Book() {
    }

    public Book(String bookName, String authorName, double price) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
        this.isAvailable=true;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", price=" + price +
                '}';
    }
}
