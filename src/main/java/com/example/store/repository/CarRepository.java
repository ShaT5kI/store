package com.example.store.repository;

import com.example.store.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select distinct c from Car c where c.id in" +
            " (select cop.car.id from Order o join CarOrderProduct cop " +
            " on o.id = cop.order.id join Product p " +
            "on cop.product.id = p.id " +
            "where p.id = :pr_id and o.isOpen = true)")
    List<Car> findCarByProductInWork(@Param("pr_id") Long productId);
}
