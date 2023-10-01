package com.example.store.repository;

import com.example.store.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
//    @Query("SELECT s FROM Stock s WHERE (:cellNumber is null OR s.cellNumber = :cellNumber) " +
//            "AND (:productId is null OR s.product.id = :productId) " +
//            "AND (:quantity is null OR s.quantity = :quantity)")
//    List<Stock> findStocksByFilters(
//            @Param("cellNumber") Integer cellNumber,
//            @Param("productId") Long productId,
//            @Param("quantity") Integer quantity
//    );

    List<Stock> findAll(Specification<Stock> spec);
}
