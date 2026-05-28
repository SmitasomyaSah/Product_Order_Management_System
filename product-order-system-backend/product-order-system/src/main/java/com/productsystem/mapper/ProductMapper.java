package com.productsystem.mapper;

import com.productsystem.dto.ProductResponse;
import com.productsystem.entity.Product;

public class ProductMapper {

    public static ProductResponse mapToResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setStatus(product.getStatus());

        return productResponse;
    }
}
