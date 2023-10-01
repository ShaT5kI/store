package com.example.store.service;

import com.example.store.entity.Car;
import com.example.store.entity.CarOrderProduct;
import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.repository.CarRepository;
import com.example.store.repository.CustomerRepository;
import jakarta.transaction.Transactional;
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
public class CarService {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CarService(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public void addNew(Car car, Long customerId) {
        var customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            car.setCustomer(customer.get());
            carRepository.save(car);
        }
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Page<Car> getAllPageable(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return carRepository.findAll(pageable);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public Car getById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cars not found with id: " + id));
    }

    public void update(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public List<Order> getOrdersForCar(Long carId) {
        var carOptional = carRepository.findById(carId);

        if (carOptional.isEmpty()) {
            return Collections.emptyList();
        }

        var car = carOptional.get();

        var carOrderProducts = car.getCarOrderProducts();
        List<Order> orders = new ArrayList<>();
        for (CarOrderProduct cop : carOrderProducts) {
            orders.add(cop.getOrder());
        }

        return orders;
    }

    @Transactional
    public List<Product> getProductsForCar(Long carId) {
        var carOptional = carRepository.findById(carId);

        if (carOptional.isEmpty()) {
            return Collections.emptyList();
        }

        var car = carOptional.get();

        var carOrderProducts = car.getCarOrderProducts();
        List<Product> orders = new ArrayList<>();
        for (CarOrderProduct cop : carOrderProducts) {
            orders.add(cop.getProduct());
        }

        return orders;
    }

}
