package com.nicmora.cleanarchitecture.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class User implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Product> products;

    public User() {
        this.products = new ArrayList<>();
    }

}
