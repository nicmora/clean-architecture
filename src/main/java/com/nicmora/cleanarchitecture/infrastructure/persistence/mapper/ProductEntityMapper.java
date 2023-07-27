package com.nicmora.cleanarchitecture.infrastructure.persistence.mapper;

import com.nicmora.cleanarchitecture.core.domain.Product;
import com.nicmora.cleanarchitecture.core.domain.constant.Category;
import com.nicmora.cleanarchitecture.infrastructure.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper implements PersistenceMapper<Product, ProductEntity> {

    @Override
    public ProductEntity mapToEntity(final Product model) {
        return ProductEntity.builder()
                .code(model.getCode())
                .name(model.getName())
                .category(model.getCategory().name())
                .price(model.getPrice())
                .build();
    }

    @Override
    public Product mapToModel(final ProductEntity entity) {
        return Product.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .category(Category.valueOf(entity.getCategory()))
                .price(entity.getPrice())
                .build();
    }

}
