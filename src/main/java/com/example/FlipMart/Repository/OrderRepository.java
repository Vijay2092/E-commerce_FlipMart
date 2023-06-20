package com.example.FlipMart.Repository;

import com.example.FlipMart.Model.Customer;
import com.example.FlipMart.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query(value = "SELECT * FROM order_info ORDER BY total_value DESC", nativeQuery = true)
    List<OrderEntity> orderWithHighestValue();

    List<OrderEntity> findByCustomer(Customer customer);

    @Query(value = "SELECT * FROM order_info WHERE customer_id = :customerId ORDER BY total_value DESC",nativeQuery = true)
    List<OrderEntity> HighestValueOrder(int customerId);

    @Query(value = "SELECT * FROM order_info WHERE customer_id = :customerId ORDER BY id DESC",nativeQuery = true)
    List<OrderEntity> RecentOrder(int customerId);
}
