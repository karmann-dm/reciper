package com.reciper.service.base;

import com.reciper.entity.base.AbstractEntity;
import com.reciper.repository.base.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository> implements CommonService<E> {
    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return null;
    }

    @Override
    public E update(E entity) {
        return null;
    }

    @Override
    public Collection<E> findAll() {
        return null;
    }

    @Override
    public E findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
