package com.example.store.controller;

import com.example.store.entity.Product;
import com.example.store.entity.Supplier;
import com.example.store.service.ProductService;
import com.example.store.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final SupplierService supplierService;

    @Autowired
    public ProductController(ProductService productService, SupplierService supplierService) {
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public String getAllProducts(Model model,
                                 @RequestParam(defaultValue = "0") int pageNo,
                                 @RequestParam(defaultValue = "3") int pageSize) {
        Page<Product> productsPage = productService.getAllPageable(pageNo, pageSize);
        List<Product> products = productsPage.getContent();

        model.addAttribute("products", products);
        model.addAttribute("totalPages", productsPage.getTotalPages());
        model.addAttribute("currentPage", pageNo);

        return "products";
    }

    @GetMapping("/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        List<Supplier> suppliers = supplierService.getAll();
        model.addAttribute("suppliers", suppliers);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") Product product,
                                @RequestParam("supplierId") Long supplierId) {
        Supplier supplier = supplierService.getById(supplierId);
        product.setSupplier(supplier);
        productService.addNew(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getById(id);
        List<Supplier> suppliers = supplierService.getAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product,
                              @RequestParam("supplierId") Long supplierId) {
        Supplier supplier = supplierService.getById(supplierId);
        product.setSupplier(supplier);
        productService.update(product);
        return "redirect:/products";
    }


}
