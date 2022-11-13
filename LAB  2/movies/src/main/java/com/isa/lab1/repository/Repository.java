package com.isa.lab1.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {

    Optional<E> find(K name);

    List<E> findAll();

    void create(E entity);
    
    void delete(E entity);

    void update(E entity);
}