package com.example.ecomApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomApp.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	

}
