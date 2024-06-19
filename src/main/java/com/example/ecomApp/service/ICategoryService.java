package com.example.ecomApp.service;

import java.util.List;
import java.util.Optional;

import com.example.ecomApp.model.Category;

public interface ICategoryService {
	//To get data From DataBase
		public List<Category> getAllCategories();	
		//To add category to DataBase
		public void addCategory(Category category);
		// To delete category 
		public void removeCategoryById(int id);
		// To get Category By Id 
		public Optional<Category> getCategoryById(int id);

}
