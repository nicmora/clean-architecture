package com.nicmora.cleanarchitecture.infrastructure.persistence.repository;

import com.nicmora.cleanarchitecture.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductH2Repository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByCode(String code);

}
