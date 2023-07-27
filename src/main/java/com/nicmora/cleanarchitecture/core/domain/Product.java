package com.nicmora.cleanarchitecture.core.domain;

import com.nicmora.cleanarchitecture.core.domain.constant.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Product implements Serializable {

    private String code;
    private String name;
    private Category category;
    private Float price;

    public Product() {
        this.code = UUID.randomUUID().toString();
    }

}
