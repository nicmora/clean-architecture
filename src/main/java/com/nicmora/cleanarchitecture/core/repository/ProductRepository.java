package com.nicmora.cleanarchitecture.core.repository;

import com.nicmora.cleanarchitecture.core.domain.Product;
import com.nicmora.cleanarchitecture.core.service.CRUDRepository;

import java.util.Optional;

public interface ProductRepository extends CRUDRepository<Product> {

    Optional<Product> findByCode(String code);

}
