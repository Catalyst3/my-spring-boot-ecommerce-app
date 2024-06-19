package com.example.ecomApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ecomApp.service.ICategoryService;
import com.example.ecomApp.service.IProductService;

@Controller
public class HomeController {
	
	@Autowired
	IProductService iProductService;
	
	@Autowired
	ICategoryService iCategoryService;
	
	@GetMapping({"/", "/home"})
	public String goToHomePage(Model model)
	{
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model)
	{
		System.out.println(iCategoryService.getAllCategories());
		model.addAttribute("categories" , iCategoryService.getAllCategories());
		model.addAttribute("products", iProductService.getAllProducts());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id)
	{
		model.addAttribute("categories" , iCategoryService.getAllCategories());
		model.addAttribute("products", iProductService.getAllProductsByCategoryID(id));
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id)
	{
		System.out.println(iProductService.getProductByID(id));
		model.addAttribute("product", iProductService.getProductByID(id).get());
		return "viewProduct";
	}
}
