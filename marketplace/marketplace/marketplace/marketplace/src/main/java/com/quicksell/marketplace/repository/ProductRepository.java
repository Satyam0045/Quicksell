package com.quicksell.marketplace.repository;

import com.quicksell.marketplace.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    // search by title
    List<Product> findByTitleContainingIgnoreCase(
            String title
    );

    // search by category
    List<Product> findByCategoryIgnoreCase(
            String category
    );

    // search by location
    List<Product> findByLocationIgnoreCase(
            String location
    );

    // filter by price less than
    List<Product> findByPriceLessThanEqual(
            Double price
    );
}
