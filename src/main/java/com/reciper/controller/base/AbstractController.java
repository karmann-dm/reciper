package com.reciper.controller.base;

import com.reciper.entity.base.AbstractEntity;
import com.reciper.service.base.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.Collection;

public class AbstractController<E extends AbstractEntity, S extends CommonService<E>> implements CommonController<E> {
    private final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> save(@Valid E entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<E> update(Integer id, @Valid E entity) {
        entity.setId(id);
        return new ResponseEntity<>(service.update(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<E>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<E> findById(Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Override
    public void deleteById(Integer id) {
        service.delete(id);
    }
}
