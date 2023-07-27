package com.nicmora.cleanarchitecture.infrastructure.delivery.controller;

import com.nicmora.cleanarchitecture.core.domain.Product;
import com.nicmora.cleanarchitecture.core.service.ProductCRUD;
import com.nicmora.cleanarchitecture.infrastructure.delivery.dto.request.ProductRequest;
import com.nicmora.cleanarchitecture.infrastructure.delivery.mapper.ProductRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductCRUD productCRUD;
    private final ProductRequestMapper productRequestMapper;

    @Autowired
    public ProductController(ProductCRUD productCRUD,
                             ProductRequestMapper productRequestMapper) {
        this.productCRUD = productCRUD;
        this.productRequestMapper = productRequestMapper;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productCRUD.getAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Product> getByCode(@PathVariable String code) {
        return productCRUD.getByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productCRUD.create(productRequestMapper.mapToModel(request)));
    }

    @PutMapping("/{code}")
    public ResponseEntity<Product> update(@PathVariable String code, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productCRUD.update(code, productRequestMapper.mapToModel(request)));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteByCode(@PathVariable String code) {
        productCRUD.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }

}
