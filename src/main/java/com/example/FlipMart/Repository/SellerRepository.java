package com.example.FlipMart.Repository;

import com.example.FlipMart.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmailId(String emailId);
}
