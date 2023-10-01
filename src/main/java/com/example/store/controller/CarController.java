package com.example.store.controller;

import com.example.store.entity.Car;
import com.example.store.entity.Customer;
import com.example.store.service.CarService;
import com.example.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public CarController(CarService carService, CustomerService customerService) {
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping
    public String getAllCars(Model model,
                                 @RequestParam(defaultValue = "0") int pageNo,
                                 @RequestParam(defaultValue = "3") int pageSize) {
        Page<Car> carsPage = carService.getAllPageable(pageNo, pageSize);
        List<Car> cars = carsPage.getContent();

        model.addAttribute("cars", cars);
        model.addAttribute("totalPages", carsPage.getTotalPages());
        model.addAttribute("currentPage", pageNo);

        return "cars";
    }

    @GetMapping("/new")
    public String showCreateCarForm(Model model) {
        model.addAttribute("car", new Car());
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "createCar";
    }

    @PostMapping("/create")
    public String createCar(@ModelAttribute("car") Car car,
                                @RequestParam("customerId") Long customerId) {
        carService.addNew(car, customerId);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        carService.deleteById(id);
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String showEditCarForm(@PathVariable("id") Long id, Model model) {
        Car car = carService.getById(id);
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        model.addAttribute("car", car);
        return "editCar";
    }

    @PostMapping("/update")
    public String updateCar(@ModelAttribute("car") Car car,
                                @RequestParam("customerId") Long customerId) {
        carService.update(car);
        return "redirect:/cars";
    }


}
