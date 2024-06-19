package com.example.ecomApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecomApp.model.Category;
import com.example.ecomApp.repository.CategoryRepository;
import com.example.ecomApp.service.ICategoryService;

@Service
public class CategoryServiceimpl implements ICategoryService  {
	@Autowired
	CategoryRepository catagoryRepository;
	//To get data From DataBase
	public List<Category> getAllCategories()
	{
		return catagoryRepository.findAll();	
		
	}
	
	//To add category to DataBase
	public void addCategory(Category category)
	{
		catagoryRepository.save(category);
	}
	// To delete category 
	public void removeCategoryById(int id)
	{
		catagoryRepository.deleteById(id);	
	}
	
	//To get Category By Id 
	public Optional<Category> getCategoryById(int id)
	{
		return catagoryRepository.findById(id);
	}
}
