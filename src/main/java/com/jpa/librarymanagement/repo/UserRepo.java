package com.jpa.librarymanagement.repo;

import com.jpa.librarymanagement.model.BookList;
import com.jpa.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Query;
import javax.persistence.EntityManager;

public interface UserRepo extends JpaRepository<User, Integer> {

//    @Query(value = "SELECT b.book_id, b.book_name, b.author_name FROM Book b WHERE b.book_id in (select r.book_id from Rental r where r.user_id =:user_id)",nativeQuery = true)
//    List<Object> getRentalsByUser(int user_id);
}