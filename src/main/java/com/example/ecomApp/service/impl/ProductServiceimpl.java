package com.example.ecomApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomApp.model.Products;
import com.example.ecomApp.repository.ProductRepository;
import com.example.ecomApp.service.IProductService;

@Service
public class ProductServiceimpl implements IProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Products> getAllProducts()
	{
		
		return productRepository.findAll();
	}

	@Override
	public void addProduct(Products products) {
		
		productRepository.save(products);
	}

	@Override
	public void removeProductByID(long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Optional<Products> getProductByID(long id) {
		
		return productRepository.findById(id);
	}

	@Override
	public List<Products> getAllProductsByCategoryID(int id) {
		
		return productRepository.findAllByCategory_Id(id);
	}
	
	

}
