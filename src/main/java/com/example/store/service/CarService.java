package com.example.store.service;

import com.example.store.entity.Car;
import com.example.store.entity.CarOrderProduct;
import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

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
