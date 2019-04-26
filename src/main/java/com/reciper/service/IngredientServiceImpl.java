package com.reciper.service;

import com.reciper.entity.Ingredient;
import com.reciper.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient findById(Integer id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new IllegalStateException("No ingredient with id = " + id));
    }

    @Override
    public Collection<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        ingredientRepository.deleteById(id);
    }
}
