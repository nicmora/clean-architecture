package com.nicmora.cleanarchitecture.infrastructure.delivery.dto.request;

import com.nicmora.cleanarchitecture.core.domain.constant.Category;
import lombok.Value;

import java.io.Serializable;

@Value
public class ProductRequest implements Serializable {

    String code;
    String name;
    Category category;
    Float price;

}
