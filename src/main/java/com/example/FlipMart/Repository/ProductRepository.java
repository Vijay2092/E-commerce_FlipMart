package com.example.FlipMart.Repository;

import com.example.FlipMart.Enum.Category;
import com.example.FlipMart.Enum.ProductStatus;
import com.example.FlipMart.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryAndPrice(Category category, int price);

    List<Product> findByCategory(Category category);

    @Query(value = "SELECT * FROM product ORDER BY price", nativeQuery = true)
    List<Product> topCheapestProduct();

    @Query(value = "SELECT * FROM product ORDER BY price DESC", nativeQuery = true)
    List<Product> topCostliestProduct();

    List<Product> findByProductStatus(ProductStatus productStatus);
}
