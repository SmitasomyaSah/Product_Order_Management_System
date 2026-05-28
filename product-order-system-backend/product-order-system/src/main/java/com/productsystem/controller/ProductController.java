package com.productsystem.controller;

import com.productsystem.dto.ProductRequest;
import com.productsystem.dto.ProductResponse;
import com.productsystem.entity.Product;
import com.productsystem.entity.Status;
import com.productsystem.mapper.ProductMapper;
import com.productsystem.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return productService.updateProduct(id,request);
    }

    @PatchMapping("/{id}/status")
    public ProductResponse updateProductStatus(@PathVariable Long id, @RequestParam Status status) {
        return productService.updateProductStatus(id,status);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
