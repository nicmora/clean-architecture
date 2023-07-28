package com.nicmora.cleanarchitecture.core.usecase.user;

import com.nicmora.cleanarchitecture.core.domain.Product;
import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.core.exception.ResourceNotFoundException;
import com.nicmora.cleanarchitecture.core.repository.ProductRepository;
import com.nicmora.cleanarchitecture.core.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
public class AddProductToUser {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public AddProductToUser(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void execute(Long userId, String code) {
        Optional.ofNullable(userId).orElseThrow(() -> new IllegalArgumentException("\"userId\" is required"));
        Optional.ofNullable(code)
                .filter(StringUtils::hasText)
                .orElseThrow(() -> new IllegalArgumentException("\"code\" is required"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product with code " + code + " not found"));

        user.getProducts().add(product);
        userRepository.save(user);
    }

}
