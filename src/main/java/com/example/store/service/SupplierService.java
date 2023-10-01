package com.example.store.service;

import com.example.store.entity.Supplier;
import com.example.store.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public void addNew(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }

    public Supplier getById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Supplier not found with id: " + id));
    }

    public void update(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public List<Supplier> getAllFree() {
        return supplierRepository.findAllFree();
    }
}
