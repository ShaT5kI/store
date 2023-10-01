package com.example.store.controller;

import com.example.store.entity.Order;
import com.example.store.entity.Owner;
import com.example.store.service.OrderService;
import com.example.store.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/new")
    public String showCreateOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "createOwner";
    }

    @GetMapping
    public String getAllOwners(Model model) {
        List<Owner> owners = ownerService.getAll();
        model.addAttribute("owners", owners);
        return "owners";
    }

    @PostMapping("/create")
    public String createOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.addNew(owner);
        return "redirect:/owners";
    }

    @PostMapping("/update")
    public String updateOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.update(owner);
        return "redirect:/owners";
    }
}
