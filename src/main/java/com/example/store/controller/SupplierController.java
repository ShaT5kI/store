package com.example.store.controller;

import com.example.store.entity.Supplier;
import com.example.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public String getAllSuppliers(Model model) {
        List<Supplier> suppliers = supplierService.getAll();
        model.addAttribute("suppliers", suppliers);
        return "suppliers";
    }

    @GetMapping("/new")
    public String showCreateSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "createSupplier";
    }

    @PostMapping("/create")
    public String createSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.addNew(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id) {
        supplierService.deleteById(id);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String showEditSupplierForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.getById(id);
        model.addAttribute("supplier", supplier);
        return "editSupplier";
    }

    @PostMapping("/update")
    public String updateSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.update(supplier);
        return "redirect:/suppliers";
    }

}