package com.example.store.repository;

import com.example.store.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select customer from Customer customer where customer.id in" +
            " (select c.id from Customer c join Order o on c.id = o.customer.id" +
            " join CarOrderProduct cop on o.id = cop.order.id" +
            " join Product p on cop.product.id = p.id" +
            " group by c.id having sum(p.price) > :pr)")
    List<Customer> findByProductSum(@Param("pr") Integer price);
}
