package com.codegym.service;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void removeById(Long id);

    T save (T t);


}
