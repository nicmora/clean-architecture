package com.nicmora.cleanarchitecture.core.usecase.user;

import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.core.exception.ResourceNotFoundException;
import com.nicmora.cleanarchitecture.core.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
public class RemoveProductFromUser {

    private final UserRepository userRepository;

    public RemoveProductFromUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Long userId, String code) {
        Optional.ofNullable(userId).orElseThrow(() -> new IllegalArgumentException("\"userId\" is required"));
        Optional.ofNullable(code)
                .filter(StringUtils::hasText)
                .orElseThrow(() -> new IllegalArgumentException("\"code\" is required"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        user.getProducts().removeIf(product -> product.getCode().equals(code));
        userRepository.save(user);
    }

}
