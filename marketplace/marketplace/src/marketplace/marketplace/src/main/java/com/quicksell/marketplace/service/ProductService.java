package com.quicksell.marketplace.service;

import com.quicksell.marketplace.entity.Product;
import com.quicksell.marketplace.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public List<Product> searchByTitle(
            String title
    ) {

        return productRepository
                .findByTitleContainingIgnoreCase(title);
    }

    public List<Product> searchByCategory(
            String category
    ) {

        return productRepository
                .findByCategoryIgnoreCase(category);
    }

    public List<Product> searchByLocation(
            String location
    ) {

        return productRepository
                .findByLocationIgnoreCase(location);
    }

    public List<Product> filterByPrice(
            Double price
    ) {

        return productRepository
                .findByPriceLessThanEqual(price);
    }
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElse(null);
    }

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

    public String deleteProduct(Long id) {

        productRepository.deleteById(id);

        return "Product Deleted Successfully";
    }
}