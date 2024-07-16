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
    
    // Handles requests to the home page ("/" or "/home")
    @GetMapping({"/", "/home"})
    public String goToHomePage(Model model) {
        return "index"; // Renders the index.html template
    }
    
    // Handles requests to the shop page ("/shop")
    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", iCategoryService.getAllCategories());
        model.addAttribute("products", iProductService.getAllProducts());
        return "shop"; // Renders the shop.html template with categories and products
    }
    
    // Handles requests to view products by category ("/shop/category/{id}")
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id) {
        model.addAttribute("categories", iCategoryService.getAllCategories());
        model.addAttribute("products", iProductService.getAllProductsByCategoryID(id));
        return "shop"; // Renders the shop.html template filtered by category ID
    }
    
    // Handles requests to view a specific product ("/shop/viewproduct/{id}")
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id) {
        model.addAttribute("product", iProductService.getProductByID(id).get());
        return "viewProduct"; // Renders the viewProduct.html template for a specific product
    }
}
