package com.example.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "owners")
public class Owner extends Person {

    @OneToOne
    private Supplier supplier;

}
