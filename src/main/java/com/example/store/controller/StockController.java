package com.example.store.controller;

import com.example.store.entity.Product;
import com.example.store.entity.Stock;
import com.example.store.repository.StockRepository;
import com.example.store.service.ProductService;
import com.example.store.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;
    private final ProductService productService;
    private final StockRepository stockRepository;

    @Autowired
    public StockController(StockService stockService, ProductService productService, StockRepository stockRepository) {
        this.stockService = stockService;
        this.productService = productService;
        this.stockRepository = stockRepository;
    }

    @GetMapping
    public String getAllStocks(Model model) {
        List<Stock> stocks = stockService.getAll();
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("stocks", stocks);
        return "stocks";
    }

    @GetMapping("/new")
    public String showCreateStockForm(Model model) {
        model.addAttribute("stock", new Stock());
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "createStock";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("stock") Stock stock,
                                @RequestParam("productId") Long productId) {
        try {
            Product product = productService.getById(productId);
            stock.setProduct(product);
            stockService.addNew(stock);
            return "redirect:/stocks";
        }
        catch (Exception e) {
            return "redirect:/error";
        }

    }

    @GetMapping("/")
    public String showStocksByCellNumber(@RequestParam(name = "cellNumber") int cellNumber, Model model) {
        List<Stock> stocks = stockRepository.findByCellNumberOrderByCellNumberAsc(cellNumber);
        model.addAttribute("stocks", stocks);
        return "stocks";
    }


    @GetMapping("/delete/{id}")
    public String deleteStock(@PathVariable("id") Long id) {
        stockService.deleteById(id);
        return "redirect:/stocks";
    }

    @GetMapping("/edit/{id}")
    public String showEditStockForm(@PathVariable("id") Long id, Model model) {
        Stock stock = stockService.getById(id);
        List<Product> products = productService.getAll();
        model.addAttribute("stock", stock);
        model.addAttribute("products", products);
        return "editStock";
    }

    @PostMapping("/update")
    public String updateStock(@ModelAttribute("stock") Stock stock,
                              @RequestParam("productId") Long productId) {
        Product product = productService.getById(productId);
        stock.setProduct(product);
        stockService.update(stock);
        return "redirect:/stocks";
    }

}
