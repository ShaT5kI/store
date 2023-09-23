package com.example.store.repository;

import com.example.store.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select car_id, customer_id from orders join cars_orders_products" +
            " on orders.id = cars_orders_products.order_id" +
            " join products on cars_orders_products.product_id = products.id" +
            " where product_id = :pr_id and orders.is_open = true")
    List<Car> findCarByProductInWork(@Param("pr_id") Long productId);
}
