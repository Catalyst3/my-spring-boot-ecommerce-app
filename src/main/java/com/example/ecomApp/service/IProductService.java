package com.example.ecomApp.service;

import java.util.List;
import java.util.Optional;

import com.example.ecomApp.model.Products;


public interface IProductService {	
	public List<Products> getAllProducts();
	public void addProduct(Products products);
	public void removeProductByID(long id);
	public Optional<Products> getProductByID(long id);
	public List<Products> getAllProductsByCategoryID(int id);
}
