package com.nicmora.cleanarchitecture.infrastructure.persistence.dao;

import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.core.repository.UserRepository;
import com.nicmora.cleanarchitecture.infrastructure.persistence.mapper.UserEntityMapper;
import com.nicmora.cleanarchitecture.infrastructure.persistence.repository.UserH2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserDAO implements UserRepository {

    private final UserH2Repository userH2Repository;
    private final UserEntityMapper userEntityMapper;

    @Autowired
    public UserDAO(UserH2Repository userH2Repository,
                   UserEntityMapper userEntityMapper) {
        this.userH2Repository = userH2Repository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public List<User> findAll() {
        return userH2Repository.findAll()
                .stream()
                .map(userEntityMapper::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userH2Repository.findById(id)
                .map(userEntityMapper::mapToModel);
    }

    @Override
    public User save(User user) {
        return userEntityMapper
                .mapToModel(userH2Repository.save(userEntityMapper.mapToEntity(user)));
    }

    @Override
    public void delete(User user) {
        userH2Repository.delete(userEntityMapper.mapToEntity(user));
    }

}
