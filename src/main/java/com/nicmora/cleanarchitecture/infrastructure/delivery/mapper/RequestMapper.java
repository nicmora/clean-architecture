package com.nicmora.cleanarchitecture.infrastructure.delivery.mapper;

import java.io.Serializable;

public interface RequestMapper<R extends Serializable, M extends Serializable> {

    M mapToModel(R request);

}
