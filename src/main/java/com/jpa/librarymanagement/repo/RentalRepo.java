package com.jpa.librarymanagement.repo;

import com.jpa.librarymanagement.model.Book;
import com.jpa.librarymanagement.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepo extends JpaRepository<Rental,Integer> {

//    //@Query("select r from Rental r where r.user_id= :user_id")
//    @Query(value = "SELECT * FROM Rental r WHERE r.user_id =(select u.user_id from User u where u.user_id =:user_id)",
//            nativeQuery = true)
//    List<Rental> getRentalsByUser(int user_id);

        @Query("select r from Rental r where r.rent_id= :rentId")
        Rental getRentalById(int rentId);

}
