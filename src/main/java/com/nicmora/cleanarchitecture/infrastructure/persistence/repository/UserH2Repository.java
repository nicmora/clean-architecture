package com.nicmora.cleanarchitecture.infrastructure.persistence.repository;

import com.nicmora.cleanarchitecture.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserH2Repository extends JpaRepository<UserEntity, Long> {

}
