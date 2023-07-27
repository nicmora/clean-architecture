package com.nicmora.cleanarchitecture.infrastructure.persistence.mapper;

import java.io.Serializable;

public interface PersistenceMapper<M extends Serializable, E extends Serializable> {

    E mapToEntity(M model);
    M mapToModel(E entity);

}
