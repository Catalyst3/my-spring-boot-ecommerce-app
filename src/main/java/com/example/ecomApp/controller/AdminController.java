package com.example.ecomApp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecomApp.dto.ProductDTO;
import com.example.ecomApp.model.Category;
import com.example.ecomApp.model.Products;
import com.example.ecomApp.service.ICategoryService;
import com.example.ecomApp.service.IProductService;

@Controller
@RequestMapping("/admin") // Set base path for all methods in this controller
public class AdminController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IProductService iProductService;

    @GetMapping // Maps to /admin
    public String goToAdminPage() {
        return "adminHome";
    }

    @GetMapping("/categories") // Maps to /admin/categories
    public String goToCategories(Model model) {
        model.addAttribute("categories", iCategoryService.getAllCategories());
        return "categories";
    }

    @GetMapping("/categories/add") // Maps to /admin/categories/add
    public String addCategories(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/categories/add") // Maps to POST /admin/categories/add
    public String postCategoriesAdd(@ModelAttribute("category") Category category) {
        iCategoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}") // Maps to /admin/categories/delete/{id}
    public String deleteCategory(@PathVariable int id) {
        iCategoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update/{id}") // Maps to /admin/categories/update/{id}
    public String updateCategory(@PathVariable int id, Model model) {
        Optional<Category> category = iCategoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404";
        }
    }

    @GetMapping("/products") // Maps to /admin/products
    public String goToProducts(Model model) {
        try {
            model.addAttribute("products", iProductService.getAllProducts());
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "products";
    }

    @GetMapping("/products/add") // Maps to /admin/products/add
    public String productAdd(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", iCategoryService.getAllCategories());
        return "productsAdd";
    }

    @PostMapping("/products/add") // Maps to POST /admin/products/add
    public String postProductAdd(
        @ModelAttribute("productDTO") ProductDTO dto,
        @RequestParam("productImage") MultipartFile file,
        @RequestParam("imgName") String imageName
    ) throws IOException {
        Products products = new Products();
        products.setProductID(dto.getId());
        products.setProductName(dto.getName());
        products.setCategory(iCategoryService.getCategoryById(dto.getCategoryId()).get());
        products.setProductPrice(dto.getPrice());
        products.setProductWeight(dto.getWeight());
        products.setProductDescription(dto.getDescription());

        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imageName;
        }
        products.setProductImageName(imageUUID);

        iProductService.addProduct(products);
        return "redirect:/admin/products";
    }

    @GetMapping("/product/delete/{id}") // Maps to /admin/product/delete/{id}
    public String deleteProducts(@PathVariable long id) {
        iProductService.removeProductByID(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/product/update/{id}") // Maps to /admin/product/update/{id}
    public String updateProduct(@PathVariable long id, Model model) {
        Products product = iProductService.getProductByID(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getProductID());
        productDTO.setName(product.getProductName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getProductPrice());
        productDTO.setWeight(product.getProductWeight());
        productDTO.setDescription(product.getProductDescription());
        productDTO.setImageName(product.getProductImageName());

        model.addAttribute("categories", iCategoryService.getAllCategories());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }
}
