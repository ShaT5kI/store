package com.example.store.repository;

import com.example.store.entity.CarOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarOrderProductRepository extends JpaRepository<CarOrderProduct, Long> {

    Optional<CarOrderProduct> findByCarIdAndOrderIdAndProductId(Long carId, Long orderId, Long productId);
}
