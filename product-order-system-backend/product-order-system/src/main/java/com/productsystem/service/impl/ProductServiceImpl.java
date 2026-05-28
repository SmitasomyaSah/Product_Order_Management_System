package com.productsystem.service.impl;

import com.productsystem.dto.ProductRequest;
import com.productsystem.dto.ProductResponse;
import com.productsystem.entity.Product;
import com.productsystem.entity.Status;
import com.productsystem.mapper.ProductMapper;
import com.productsystem.repository.ProductRepository;
import com.productsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest request) {

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setStatus(Status.ENABLED);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found with the id: "+id));
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Product savedProduct = productRepository.save(product);

        return ProductMapper.mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProductStatus(Long id, Status status) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found with id: " + id));
        product.setStatus(status);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> response = new ArrayList<>();
        for(Product product : products){
            response.add(ProductMapper.mapToResponse(product));
        }
        return response;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found with id: " + id));
        return ProductMapper.mapToResponse(product);
    }
}
