package com.jpa.librarymanagement.repo;

import com.jpa.librarymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
}
