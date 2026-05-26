package com.quicksell.marketplace.controller;

import com.quicksell.marketplace.entity.Product;
import com.quicksell.marketplace.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add Product
    @PostMapping("/add")
    public Product addProduct(
            @RequestBody Product product,
            Authentication authentication
    ) {

        String email =
                authentication.getName();

        return productService.addProduct(
                product,
                email
        );
    }

    // Get All Products
    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    // Search By Title
    @GetMapping("/search/title")
    public List<Product> searchByTitle(
            @RequestParam String title
    ) {

        return productService.searchByTitle(title);
    }

    // Search By Category
    @GetMapping("/search/category")
    public List<Product> searchByCategory(
            @RequestParam String category
    ) {

        return productService.searchByCategory(category);
    }

    // Search By Location
    @GetMapping("/search/location")
    public List<Product> searchByLocation(
            @RequestParam String location
    ) {

        return productService.searchByLocation(location);
    }

    // Filter By Price
    @GetMapping("/filter/price")
    public List<Product> filterByPrice(
            @RequestParam Double price
    ) {

        return productService.filterByPrice(price);
    }

    // Get Product By ID
    @GetMapping("/{id}")
    public Product getProductById(
            @PathVariable Long id
    ) {

        return productService.getProductById(id);
    }

    // Update Product
    @PutMapping("/update/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {

        return productService.updateProduct(
                id,
                product
        );
    }

    // Delete Product
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {

        return productService.deleteProduct(id);
    }

    @GetMapping("/admin")
    public String adminAccess(
            Authentication authentication
    ) {

        return "Welcome Admin: "
                + authentication.getName();
    }
}