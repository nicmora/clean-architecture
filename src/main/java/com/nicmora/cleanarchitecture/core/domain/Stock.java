package com.nicmora.cleanarchitecture.core.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Stock implements Serializable {

    private String code;
    private Integer count;

}
