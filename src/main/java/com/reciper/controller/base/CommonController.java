package com.reciper.controller.base;

import com.reciper.entity.base.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

public interface CommonController<E extends AbstractEntity> {
    @PostMapping
    ResponseEntity<E> save(@RequestBody @Valid E entity);

    @GetMapping
    ResponseEntity<Collection<E>> findAll();

    @PutMapping("/{id}")
    ResponseEntity<E> update(@PathVariable Integer id, @RequestBody @Valid E entity);

    @GetMapping("/{id}")
    ResponseEntity<E> findById(@PathVariable Integer id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Integer id);
}
