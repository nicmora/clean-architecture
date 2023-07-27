package com.nicmora.cleanarchitecture.core.service;

import java.util.List;

public interface CRUDRepository<T> {

    List<T> findAll();
    T save(T model);
    void delete(T model);

}
