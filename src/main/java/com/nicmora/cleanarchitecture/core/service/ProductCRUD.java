package com.nicmora.cleanarchitecture.core.service;

import com.nicmora.cleanarchitecture.core.domain.Product;
import com.nicmora.cleanarchitecture.core.exception.ResourceNotFoundException;
import com.nicmora.cleanarchitecture.core.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductCRUD {

    private final ProductRepository productRepository;

    public ProductCRUD(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getByCode(String code) {
        return productRepository.findByCode(code);
    }

    public Product create(Product product) {
        Optional<String> code = Optional.ofNullable(product.getCode());
        if (code.isPresent() && productRepository.findByCode(code.get()).isPresent()) {
            throw new IllegalArgumentException("Product with code " + code.get() + " already exists");
        }

        product.setCode(code.orElse(createUUID()));

        return productRepository.save(product);
    }

    public Product update(String code, Product product) {
        return productRepository.findByCode(code)
                .map(found -> {
                    found.setName(product.getName());
                    found.setCategory(product.getCategory());
                    found.setPrice(product.getPrice());
                    return found;
                })
                .map(productRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Product with code " + code + " not exists"));
    }

    public void deleteByCode(String code) {
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product with code " + code + " not exists"));

        productRepository.delete(product);
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

}
