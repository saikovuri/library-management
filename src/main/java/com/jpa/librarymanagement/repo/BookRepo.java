package com.jpa.librarymanagement.repo;

import com.jpa.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {

    @Modifying
    @Query("update Book b set b.isAvailable = :is_available where b.book_id = :book_id")
    void updateBookStatusToNotAvailable(int book_id, boolean is_available);

    @Modifying
    @Query("update Book b set b.isAvailable = :is_available where b.book_id = :book_id")
    void updateBookStatusToAvailable(int book_id, boolean is_available);

    @Query("select b from Book b where b.isAvailable=true")
    List<Book> getAllAvailableBook();

    @Query("select b from Book b where b.authorName= :authorName")
    List<Book> getAllBookByAuthorName(String authorName);

    @Query("select b from Book b where b.bookName= :bookName")
    Book getBookByName(String bookName);

    @Query(value = "SELECT b.is_available from Book b where b.book_id=:book_id",
           nativeQuery = true)
    boolean isAvailable(int book_id);

}

