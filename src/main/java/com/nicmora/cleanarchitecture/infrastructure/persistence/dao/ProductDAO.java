package com.nicmora.cleanarchitecture.infrastructure.persistence.dao;

import com.nicmora.cleanarchitecture.core.domain.Product;
import com.nicmora.cleanarchitecture.core.repository.ProductRepository;
import com.nicmora.cleanarchitecture.infrastructure.persistence.repository.ProductH2Repository;
import com.nicmora.cleanarchitecture.infrastructure.persistence.mapper.ProductEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductDAO implements ProductRepository {

    private final ProductH2Repository productH2Repository;
    private final ProductEntityMapper productEntityMapper;

    @Autowired
    public ProductDAO(ProductH2Repository productH2Repository,
                      ProductEntityMapper productEntityMapper) {
        this.productH2Repository = productH2Repository;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public List<Product> findAll() {
        return productH2Repository.findAll()
                .stream()
                .map(productEntityMapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findByCode(String code) {
        return productH2Repository.findByCode(code)
                .map(productEntityMapper::mapToModel);
    }

    @Override
    public Product save(Product product) {
        return productEntityMapper
                .mapToModel(productH2Repository.save(productEntityMapper.mapToEntity(product)));
    }

    @Override
    public void delete(Product product) {
        productH2Repository.findByCode(product.getCode())
                        .ifPresent(productH2Repository::delete);
    }

}
