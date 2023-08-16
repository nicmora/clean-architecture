package com.nicmora.cleanarchitecture.infrastructure.persistence.mapper;

import com.nicmora.cleanarchitecture.core.domain.Stock;
import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.infrastructure.persistence.entity.StockEntity;
import com.nicmora.cleanarchitecture.infrastructure.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StockEntityMapper implements PersistenceMapper<Stock, StockEntity> {

    @Override
    public StockEntity mapToEntity(Stock model) {
        return StockEntity.builder()
                .code(model.getCode())
                .count(model.getCount())
                .build();

    }

    @Override
    public Stock mapToModel(StockEntity entity) {
        return Stock.builder()
                .code(entity.getCode())
                .count(entity.getCount())
                .build();
    }

}
