package com.example.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StoreController {
    @GetMapping("/")
    public String getStore() { return "store"; }

}
