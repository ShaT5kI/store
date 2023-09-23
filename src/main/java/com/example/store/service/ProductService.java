package com.example.store.service;

import com.example.store.ProcedureCaller;
import com.example.store.entity.Car;
import com.example.store.entity.CarOrderProduct;
import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProcedureCaller procedureCaller;

    @Autowired
    public ProductService(ProductRepository productRepository, ProcedureCaller procedureCaller) {
        this.productRepository = productRepository;
        this.procedureCaller = procedureCaller;
    }

    public void addNew(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Page<Product> getAllPageable(int pageNo, int pageSize) {
//        procedureCaller.call();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageable);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Products not found with id: " + id));
    }

    public void update(Product product) {
        productRepository.save(product);
    }

    public List<Car> getCarsForProduct(Long carId) {
        var productOptional = productRepository.findById(carId);

        if (productOptional.isEmpty()) {
            return Collections.emptyList();
        }

        var order = productOptional.get();

        var carOrderProducts = order.getCarOrderProducts();
        List<Car> cars = new ArrayList<>();
        for (CarOrderProduct cop : carOrderProducts) {
            cars.add(cop.getCar());
        }

        return cars;
    }

    public List<Order> getOrdersForProduct(Long carId) {
        var productOptional = productRepository.findById(carId);

        if (productOptional.isEmpty()) {
            return Collections.emptyList();
        }

        var product = productOptional.get();

        var carOrderProducts = product.getCarOrderProducts();
        List<Order> orders = new ArrayList<>();
        for (CarOrderProduct cop : carOrderProducts) {
            orders.add(cop.getOrder());
        }

        return orders;
    }

}
