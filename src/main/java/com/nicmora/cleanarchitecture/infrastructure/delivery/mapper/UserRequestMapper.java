package com.nicmora.cleanarchitecture.infrastructure.delivery.mapper;

import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.infrastructure.delivery.dto.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserRequestMapper implements RequestMapper<UserRequest, User> {

    @Override
    public User mapToModel(UserRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .products(new ArrayList<>())
                .build();
    }

}
