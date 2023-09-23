package com.example.store.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    @ManyToMany
    private Set<Product> products;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> productSet) {
        this.products = productSet;
    }
}