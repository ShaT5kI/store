package com.example.store.repository;

import com.example.store.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findById(Long id);

    @Query("select distinct s from Supplier s where s.id in" +
            " (select s.id from Supplier s left outer join Owner o on s.id = o.supplier.id " +
            "where o.supplier.id is null)")
    List<Supplier> findAllFree();
}