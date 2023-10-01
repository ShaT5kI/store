package com.example.store.repository;

import com.example.store.entity.CarOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarOrderProductRepository extends JpaRepository<CarOrderProduct, Long> {
}
