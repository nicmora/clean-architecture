package com.nicmora.cleanarchitecture.infrastructure.delivery.controller;

import com.nicmora.cleanarchitecture.core.domain.Stock;
import com.nicmora.cleanarchitecture.core.service.StockCRUD;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    final private StockCRUD stockCRUD;

    public StockController(StockCRUD stockCRUD) {
        this.stockCRUD = stockCRUD;
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAll() {
        return ResponseEntity.ok(stockCRUD.getAll());
    }

    @GetMapping
    public ResponseEntity<Stock> getAll(@RequestParam String code) {
        return stockCRUD.getByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
