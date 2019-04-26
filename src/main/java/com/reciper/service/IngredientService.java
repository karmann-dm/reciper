package com.reciper.service;

import com.reciper.entity.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Ingredient create(Ingredient ingredient);
    Ingredient update(Ingredient ingredient);
    Ingredient findById(Integer id);
    Collection<Ingredient> findAll();
    void delete(Integer id);
}
