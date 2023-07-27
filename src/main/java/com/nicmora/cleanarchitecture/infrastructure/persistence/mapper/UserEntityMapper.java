package com.nicmora.cleanarchitecture.infrastructure.persistence.mapper;

import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.infrastructure.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserEntityMapper implements PersistenceMapper<User, UserEntity> {

    private final ProductEntityMapper productEntityMapper;

    @Autowired
    public UserEntityMapper(ProductEntityMapper productEntityMapper) {
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public UserEntity mapToEntity(User model) {
        return UserEntity.builder()
                .id(model.getId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .email(model.getEmail())
                .products(model.getProducts()
                        .stream()
                        .map(productEntityMapper::mapToEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public User mapToModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .products(entity.getProducts()
                        .stream()
                        .map(productEntityMapper::mapToModel)
                        .collect(Collectors.toList()))
                .build();
    }

}
