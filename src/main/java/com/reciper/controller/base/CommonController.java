package com.reciper.controller.base;

import com.reciper.entity.base.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CommonController<E extends AbstractEntity> {
    @PostMapping
    ResponseEntity<E> save(@RequestBody @Valid E entity);
}
