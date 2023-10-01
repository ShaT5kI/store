package com.example.store.service;

import com.example.store.entity.Car;
import com.example.store.entity.CarOrderProduct;
import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.repository.CarOrderProductRepository;
import com.example.store.repository.CarRepository;
import com.example.store.repository.OrderRepository;
import com.example.store.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private CarRepository carRepository;
    private ProductRepository productRepository;

    private CarOrderProductRepository carOrderProductRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addNew(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Orders not found with id: " + id));
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public List<Car> getCarsForOrder(Long carId) {
        var orderOptional = orderRepository.findById(carId);

        if (orderOptional.isEmpty()) {
            return Collections.emptyList();
        }

        var order = orderOptional.get();

        var carOrderProducts = order.getCarOrderProducts();
        List<Car> cars = new ArrayList<>();
        for (CarOrderProduct cop : carOrderProducts) {
            cars.add(cop.getCar());
        }

        return cars;
    }

    @Transactional
    public List<Product> getProductsForOrder(Long carId) {
        var orderOptional = orderRepository.findById(carId);

        if (orderOptional.isEmpty()) {
            return Collections.emptyList();
        }

        var order = orderOptional.get();

        var carOrderProducts = order.getCarOrderProducts();
        List<Product> products = new ArrayList<>();
        for (CarOrderProduct cop : carOrderProducts) {
            products.add(cop.getProduct());
        }

        return products;
    }

    public void addProductAndCar(Order order, Long carId, Long productId) {
        CarOrderProduct carOrderProduct = new CarOrderProduct();

        Optional<Car> car = carRepository.findById(carId);
        Optional<Product> product = productRepository.findById(productId);

        if (car.isPresent() && product.isPresent()) {
            carOrderProduct.setCar(car.get());
            carOrderProduct.setProduct(product.get());
            carOrderProduct.setOrder(order);
            carOrderProductRepository.save(carOrderProduct);
        }
    }
}
