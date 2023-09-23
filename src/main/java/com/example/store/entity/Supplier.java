package com.example.store.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private boolean documentsPackage;
    private boolean guarantee;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDocumentsPackage() {
        return documentsPackage;
    }

    public void setDocumentsPackage(boolean documentsPackage) {
        this.documentsPackage = documentsPackage;
    }

    public boolean isGuarantee() {
        return guarantee;
    }

    public void setGuarantee(boolean guarantee) {
        this.guarantee = guarantee;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}