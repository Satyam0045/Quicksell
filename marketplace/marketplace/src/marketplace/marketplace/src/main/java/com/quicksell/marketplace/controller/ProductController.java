package com.quicksell.marketplace.controller;

import com.quicksell.marketplace.entity.Product;
import com.quicksell.marketplace.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(
            @RequestBody Product product
    ) {

        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/search/title")
    public List<Product> searchByTitle(
            @RequestParam String title
    ) {

        return productService.searchByTitle(title);
    }

    @GetMapping("/search/category")
    public List<Product> searchByCategory(
            @RequestParam String category
    ) {

        return productService.searchByCategory(category);
    }

    @GetMapping("/search/location")
    public List<Product> searchByLocation(
            @RequestParam String location
    ) {

        return productService.searchByLocation(location);
    }

    @GetMapping("/filter/price")
    public List<Product> filterByPrice(
            @RequestParam Double price
    ) {

        return productService.filterByPrice(price);
    }
    // get product by id
    @GetMapping("/{id}")
    public Product getProductById(
            @PathVariable Long id
    ) {

        return productService.getProductById(id);
    }

    // update product
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

    // delete product
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {

        return productService.deleteProduct(id);
    }
}