package com.nicmora.cleanarchitecture.infrastructure.delivery.controller;

import com.nicmora.cleanarchitecture.core.domain.User;
import com.nicmora.cleanarchitecture.core.service.UserCRUD;
import com.nicmora.cleanarchitecture.core.usecase.user.AddProductToUser;
import com.nicmora.cleanarchitecture.core.usecase.user.RemoveProductFromUser;
import com.nicmora.cleanarchitecture.infrastructure.delivery.dto.request.ProductRequest;
import com.nicmora.cleanarchitecture.infrastructure.delivery.dto.request.UserRequest;
import com.nicmora.cleanarchitecture.infrastructure.delivery.mapper.UserRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserCRUD userCRUD;
    private final UserRequestMapper userRequestMapper;
    private final AddProductToUser addProductToUser;
    private final RemoveProductFromUser removeProductFromUser;

    @Autowired
    public UserController(UserCRUD userCRUD,
                          UserRequestMapper userRequestMapper,
                          AddProductToUser addProductToUser,
                          RemoveProductFromUser removeProductFromUser) {
        this.userCRUD = userCRUD;
        this.userRequestMapper = userRequestMapper;
        this.addProductToUser = addProductToUser;
        this.removeProductFromUser = removeProductFromUser;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userCRUD.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userCRUD.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userCRUD.create(userRequestMapper.mapToModel(request)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userCRUD.update(id, userRequestMapper.mapToModel(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userCRUD.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/addProduct")
    public ResponseEntity<Void> addProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        addProductToUser.execute(id, productRequest.getCode());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/removeProduct")
    public ResponseEntity<Void> removeProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        removeProductFromUser.execute(id, productRequest.getCode());
        return ResponseEntity.noContent().build();
    }

}
