package com.nicmora.cleanarchitecture.infrastructure.persistence.dao;

import com.nicmora.cleanarchitecture.core.domain.Stock;
import com.nicmora.cleanarchitecture.core.repository.StockRepository;
import com.nicmora.cleanarchitecture.infrastructure.persistence.mapper.StockEntityMapper;
import com.nicmora.cleanarchitecture.infrastructure.persistence.repository.StockH2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StockDAO implements StockRepository {

    private final StockH2Repository stockH2Repository;
    private final StockEntityMapper stockEntityMapper;

    @Autowired
    public StockDAO(StockH2Repository stockH2Repository,
                    StockEntityMapper stockEntityMapper) {
        this.stockH2Repository = stockH2Repository;
        this.stockEntityMapper = stockEntityMapper;
    }

    @Override
    public List<Stock> findAll() {
        return stockH2Repository.findAll()
                .stream()
                .map(stockEntityMapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Stock> findByCode(String code) {
        return stockH2Repository.findByCode(code)
                .map(stockEntityMapper::mapToModel);
    }

    @Override
    public Stock save(Stock model) {
        return stockEntityMapper.mapToModel(stockH2Repository.save(stockEntityMapper.mapToEntity(model)));
    }

    @Override
    public void delete(Stock model) {
        stockH2Repository.delete(stockEntityMapper.mapToEntity(model));
    }

}
