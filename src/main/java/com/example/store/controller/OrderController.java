package com.example.store.controller;

import com.example.store.entity.Car;
import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.entity.Supplier;
import com.example.store.service.CarService;
import com.example.store.service.OrderService;
import com.example.store.service.ProductService;
import com.example.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final SupplierService supplierService;

    private final CarService carService;

    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, SupplierService supplierService, CarService carService, ProductService productService) {
        this.orderService = orderService;
        this.supplierService = supplierService;
        this.carService = carService;
        this.productService = productService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/new")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new Order());
        List<Supplier> suppliers = supplierService.getAll();
        model.addAttribute("suppliers", suppliers);
        return "createOrder";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute("order") Order order,
                                @RequestParam("supplierId") Long supplierId) {
        Supplier supplier = supplierService.getById(supplierId);
        order.setSupplier(supplier);
        orderService.addNew(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        return "editOrder";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute("order") Order order) {
        orderService.update(order);
        return "redirect:/orders";
    }

    @PostMapping("/addProductAndCar")
    public String addProductAndCar(@RequestParam("id") Long orderId,
                                   @RequestParam("carId") Long carId,
                                   @RequestParam("productId") Long productId) {
        orderService.addProductAndCar(orderId, carId, productId);
        return "redirect:/orders";
    }

    @GetMapping("/addProductAndCar/{id}")
    public String showAddProductAndCar(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getById(id);
        List<Car> cars = carService.getAll();
        List<Product> products = productService.getAll();
        model.addAttribute("order", order);
        model.addAttribute("cars", cars);
        model.addAttribute("products", products);
        return "addProductAndCar";
    }

}
