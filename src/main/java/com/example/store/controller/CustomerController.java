package com.example.store.controller;

import com.example.store.entity.Customer;
import com.example.store.entity.Order;
import com.example.store.entity.Supplier;
import com.example.store.service.CustomerService;
import com.example.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/new")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        List<Order> orders = orderService.getAll();
        model.addAttribute("suppliers", orders);
        return "createCustomer";
    }

    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute("customer") Customer customer,
                                 @RequestParam("orderId") Long orderId) {
        customerService.addNew(customer);
        return "redirect:/customers";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer,
                                 @RequestParam("orderId") Long orderId) {
        customerService.update(customer);
        return "redirect:/customers";
    }
}
