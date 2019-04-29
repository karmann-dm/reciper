package com.reciper.service.base;

import com.reciper.entity.base.AbstractEntity;
import com.reciper.repository.base.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {
    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public E update(E entity) {
        repository
                .findById(entity.getId())
                .orElseThrow(() ->
                        new IllegalStateException("No entity with id = " + entity.getId()
                        )
                );
        return repository.save(entity);
    }

    @Override
    public Collection<E> findAll() {
        return repository.findAll();
    }

    @Override
    public E findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("No entity with id = " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
