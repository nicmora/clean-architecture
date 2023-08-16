package com.nicmora.cleanarchitecture.infrastructure.persistence.repository;

import com.nicmora.cleanarchitecture.infrastructure.persistence.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockH2Repository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByCode(String code);

}
