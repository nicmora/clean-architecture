package com.nicmora.cleanarchitecture.core.service;

import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.core.exception.ResourceNotFoundException;
import com.nicmora.cleanarchitecture.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCRUD {

    private final UserRepository userRepository;

    public UserCRUD(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        Optional<Long> id = Optional.ofNullable(user.getId());
        if (id.isPresent() && userRepository.findById(id.get()).isPresent()) {
            throw new IllegalArgumentException("User with id " + id + " already exists");
        }

        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        return userRepository.findById(id)
                .map(found -> {
                    found.setFirstName(user.getFirstName());
                    found.setLastName(user.getLastName());
                    found.setEmail(user.getEmail());
                    return found;
                })
                .map(userRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not exists"));
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not exists"));

        userRepository.delete(user);
    }

}
