package com.productsystem.service;

import com.productsystem.dto.ProductRequest;
import com.productsystem.dto.ProductResponse;
import com.productsystem.entity.Status;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest request);

    ProductResponse updateProduct(Long id,ProductRequest request);

    ProductResponse updateProductStatus(Long id, Status status);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);
}
