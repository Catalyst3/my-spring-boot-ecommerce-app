package com.example.ecomApp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecomApp.dto.ProductDTO;
import com.example.ecomApp.model.Category;
import com.example.ecomApp.model.Products;
import com.example.ecomApp.service.ICategoryService;
import com.example.ecomApp.service.IProductService;

@Controller
public class AdminController {
    
	 
	public static String uploadDirectory = System.getProperty("user.dir")+ "/src/main/resources/static/productImages";
    @Autowired
    ICategoryService iCategoryService;
    
    @Autowired
    IProductService iProductService;
    
    // Method to navigate to the admin home page
    @GetMapping("/admin")
    public String goToAdminPage() {
        return "adminHome";
    }
    
    // Method to navigate to the categories page and display all categories
    @GetMapping("/admin/categories")
    public String goToCategories(Model model) {
        model.addAttribute("categories", iCategoryService.getAllCategories());
        return "categories";
    }
    
    // Method to navigate to the add category page
    @GetMapping("/admin/categories/add")
    public String addCategories(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    
    // Method to handle the submission of the add category form
    @PostMapping("/admin/categories/add")
    public String postCategoriesAdd(@ModelAttribute("category") Category category) {
        iCategoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    
   
    
    // Method to handle the deletion of a category by its ID
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id)
    {
    	iCategoryService.removeCategoryById(id);
    	return "redirect:/admin/categories";
    }
    
    // Method to handle the update of a category by its ID
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model)
    {
    	Optional<Category> category = iCategoryService.getCategoryById(id);
    	if(category.isPresent())
    	{
    		model.addAttribute("category", category.get());
    		return "categoriesAdd";
    	}
    	else
    	{
    		return "404";
    	}
    	
    }
    
//                                       Products Section
    // Method to navigate to the products page
    @GetMapping("/admin/products")
    public String goToProducts(Model model) {
    	try {
    		model.addAttribute("products", iProductService.getAllProducts());
//    		System.out.println(iProductService.getAllProducts());
			
		} catch (Exception e) {
			System.out.println("Error"+e);
		}
    	
        return "products";
    }
    
    @GetMapping("/admin/products/add")
    public String productAdd(Model model)
    {
    	model.addAttribute("productDTO", new ProductDTO());
    	model.addAttribute("categories", iCategoryService.getAllCategories());
    	return "productsAdd";
  
    }
    
    @PostMapping("/admin/products/add")
    public String postProductAdd(@ModelAttribute ("productDTO") ProductDTO dto,// This binds the form data to the ProductDTO object.
    		@RequestParam ("productImage") MultipartFile file,// This binds the uploaded file (product image) to the MultipartFile object.
    		@RequestParam ("imgName") String imageName) // This binds the request parameter "imgName" to the String variable imageName.
    				throws IOException // This indicates that the method might throw an IOException.
    {
    	Products products = new Products();
    	
    	products.setProductID(dto.getId());
    	products.setProductName(dto.getName());
    	products.setCategory(iCategoryService.getCategoryById(dto.getCategoryId()).get());
    	products. setProductPrice(dto.getPrice());
    	products. setProductWeight(dto.getWeight());
    	products.setProductDescription(dto.getDescription());
    	// Variable to hold the unique identifier for the image
    	String imageUUID;

    	// Check if the uploaded file is not empty
    	if (!file.isEmpty()) {
    	    // Get the original filename of the uploaded file
    	    imageUUID = file.getOriginalFilename();
    	    
    	    // Create a Path object by combining the upload directory and the original filename
    	    Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
    	    
    	    // Write the file bytes to the specified path, effectively saving the file to the directory
    	    Files.write(fileNameAndPath, file.getBytes());
    	} else {
    	    // If no file is uploaded, use the provided image name as the image UUID
    	    imageUUID = imageName;
    	}

    	// Set the product image name in the products object to the imageUUID
    	// This ensures the correct image name is associated with the product, whether it's a new upload or an existing image name
    	products.setProductImageName(imageUUID);

    	// Call the service method to add the product to the database
    	iProductService.addProduct(products);

    	
    	
    	return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProducts(@PathVariable long id)
    {
    	iProductService.removeProductByID(id);
    	return "redirect:/admin/products";
    }
    
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model)
    {
    	Products product = iProductService.getProductByID(id).get();
    	ProductDTO productDTO = new ProductDTO();
    	productDTO.setId(product.getProductID());
    	productDTO.setName(product.getProductName());
    	productDTO.setCategoryId(product.getCategory().getId());
    	productDTO.setPrice(product.getProductPrice());
    	productDTO.setWeight(product.getProductWeight());
    	productDTO.setDescription(product.getProductDescription());
    	productDTO.setImageName(product.getProductImageName());
    	
    	model.addAttribute("categories" , iCategoryService.getAllCategories());
    	model.addAttribute("productDTO",productDTO);
    	return "productsAdd";
    }
    
    
}
