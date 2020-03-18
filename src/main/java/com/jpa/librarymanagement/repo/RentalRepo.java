package com.jpa.librarymanagement.repo;

import com.jpa.librarymanagement.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentalRepo extends JpaRepository<Rental,Integer> {


//    @Query(value = "INSERT INTO rental VALUE (2,2, CURDATE(),  CURDATE(),2 )", nativeQuery = true)
//    void rentBook(Rental rental);

}
