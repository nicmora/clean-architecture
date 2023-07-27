package com.nicmora.cleanarchitecture.infrastructure.delivery.mapper;

import com.nicmora.cleanarchitecture.core.domain.Product;
import com.nicmora.cleanarchitecture.infrastructure.delivery.dto.request.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper implements RequestMapper<ProductRequest, Product> {

    @Override
    public Product mapToModel(ProductRequest request) {
        return Product.builder()
                .code(request.getCode())
                .name(request.getName())
                .category(request.getCategory())
                .price(request.getPrice())
                .build();
    }

}
