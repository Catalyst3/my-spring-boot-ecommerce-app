package com.example.ecomApp.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomApp.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// Method to find a user by email
    Optional<User> findByUserEmail(String userEmail);
    
    // Method to check if a user exists by email
    boolean existsByUserEmail(String userEmail);
}
