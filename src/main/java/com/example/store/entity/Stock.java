package com.example.store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int cellNumber;

    @Positive(message = "Число должно быть больше нуля")
    @Column(nullable = false)
    private int quantity;

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}