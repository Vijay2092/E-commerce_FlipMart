package com.example.FlipMart.Repository;

import com.example.FlipMart.Enum.Gender;
import com.example.FlipMart.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmailId(String emailId);

    List<Customer> findByGender(Gender gender);
}
