package com.nicmora.cleanarchitecture.core.service;

import com.nicmora.cleanarchitecture.core.domain.Stock;
import com.nicmora.cleanarchitecture.core.exception.ResourceNotFoundException;
import com.nicmora.cleanarchitecture.core.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockCRUD {

    private final StockRepository stockRepository;

    public StockCRUD(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getByCode(String code) {
        return stockRepository.findByCode(code);
    }

    public Stock create(Stock stock) {
        String code = stock.getCode();
        stockRepository.findByCode(code)
                .ifPresent(found -> { throw new IllegalArgumentException("Stock with product code " + code + " already exists"); });
        
        return stockRepository.save(stock);
    }

    public Stock update(String code, Integer count) {
        return stockRepository.findByCode(code)
                .map(found -> {
                    found.setCount(count);
                    return found;
                })
                .map(stockRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Stock with product code " + code + " not exists"));
    }

    public void delete(String code) {
        Stock stock = stockRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Stock with product code " + code + " not exists"));

        stockRepository.delete(stock);
    }

}
