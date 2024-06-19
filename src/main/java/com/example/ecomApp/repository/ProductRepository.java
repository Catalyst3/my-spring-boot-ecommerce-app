package com.example.ecomApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomApp.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
	
	List<Products> findAllByCategory_Id(int categoryId);
}
