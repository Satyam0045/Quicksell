package com.quicksell.marketplace.service;

import com.quicksell.marketplace.entity.Product;
import com.quicksell.marketplace.entity.User;

import com.quicksell.marketplace.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    // Add Product
    public Product addProduct(
            Product product,
            String email
    ) {

        User user =
                userService.getUserByEmail(email);

        product.setUser(user);

        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    // Search By Title
    public List<Product> searchByTitle(
            String title
    ) {

        return productRepository
                .findByTitleContainingIgnoreCase(title);
    }

    // Search By Category
    public List<Product> searchByCategory(
            String category
    ) {

        return productRepository
                .findByCategoryIgnoreCase(category);
    }

    // Search By Location
    public List<Product> searchByLocation(
            String location
    ) {

        return productRepository
                .findByLocationIgnoreCase(location);
    }

    // Filter By Price
    public List<Product> filterByPrice(
            Double price
    ) {

        return productRepository
                .findByPriceLessThanEqual(price);
    }

    // Get Product By ID
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElse(null);
    }

    // Update Product
    public Product updateProduct(
            Long id,
            Product updatedProduct
    ) {

        Product existingProduct =
                productRepository.findById(id)
                        .orElse(null);

        if(existingProduct != null) {

            existingProduct.setTitle(
                    updatedProduct.getTitle()
            );

            existingProduct.setDescription(
                    updatedProduct.getDescription()
            );

            existingProduct.setPrice(
                    updatedProduct.getPrice()
            );

            existingProduct.setCategory(
                    updatedProduct.getCategory()
            );

            existingProduct.setImageUrl(
                    updatedProduct.getImageUrl()
            );

            existingProduct.setLocation(
                    updatedProduct.getLocation()
            );

            return productRepository.save(existingProduct);
        }

        return null;
    }

    // Delete Product
    public String deleteProduct(Long id) {

        productRepository.deleteById(id);

        return "Product Deleted Successfully";
    }
}