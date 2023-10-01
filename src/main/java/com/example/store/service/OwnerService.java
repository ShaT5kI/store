package com.example.store.service;

import com.example.store.entity.Owner;
import com.example.store.repository.OwnerRepository;
import com.example.store.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, PersonRepository personRepository) {
        this.ownerRepository = ownerRepository;
    }

    public void addNew(Owner owner) {
        ownerRepository.save(owner);
    }

    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    public Owner getById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Owners not found with id: " + id));
    }

    public void update(Owner owner) {
        ownerRepository.save(owner);
    }
}
