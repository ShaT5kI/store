package com.example.store.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<CarOrderProduct> carOrderProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<CarOrderProduct> getCarOrderProducts() {
        return carOrderProducts;
    }
}
