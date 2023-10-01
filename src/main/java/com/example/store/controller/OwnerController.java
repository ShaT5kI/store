package com.example.store.controller;

import com.example.store.entity.Owner;
import com.example.store.entity.Supplier;
import com.example.store.service.OwnerService;
import com.example.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final SupplierService supplierService;

    @Autowired
    public OwnerController(OwnerService ownerService, SupplierService supplierService) {
        this.ownerService = ownerService;
        this.supplierService = supplierService;
    }

    @GetMapping("/new")
    public String showCreateOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        List<Supplier> suppliers = supplierService.getAllFree();
        model.addAttribute("suppliers", suppliers);
        return "createOwner";
    }

    @GetMapping
    public String getAllOwners(Model model) {
        List<Owner> owners = ownerService.getAll();
        model.addAttribute("owners", owners);
        return "owners";
    }

    @PostMapping("/create")
    public String createOwner(@ModelAttribute("owner") Owner owner,
                              @RequestParam("supplierId") Long supplierId) {
        ownerService.addNew(owner, supplierId);
        return "redirect:/owners";
    }

    @PostMapping("/update")
    public String updateOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.update(owner);
        return "redirect:/owners";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwner(@PathVariable("id") Long id) {
        ownerService.deleteById(id);
        return "redirect:/owners";
    }
}
