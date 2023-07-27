package com.nicmora.cleanarchitecture.core.repository;

import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.core.service.CRUDRepository;

import java.util.Optional;

public interface UserRepository extends CRUDRepository<User> {

    Optional<User> findById(Long id);

}
