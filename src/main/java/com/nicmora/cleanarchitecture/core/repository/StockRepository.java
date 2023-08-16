package com.nicmora.cleanarchitecture.core.repository;

import com.nicmora.cleanarchitecture.core.domain.Stock;
import com.nicmora.cleanarchitecture.core.service.CRUDRepository;

import java.util.Optional;

public interface StockRepository extends CRUDRepository<Stock> {

    Optional<Stock> findByCode(String code);

}
