package com.reciper.service.base;

import com.reciper.entity.base.AbstractEntity;

import java.util.Collection;

public interface CommonService<E extends AbstractEntity> {
    E save(E entity);
    E update(E entity);
    E findById(Integer id);
    Collection<E> findAll();
    void delete(Integer id);
}
