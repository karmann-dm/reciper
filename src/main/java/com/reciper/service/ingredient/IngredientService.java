package com.reciper.service.ingredient;

import com.reciper.entity.Ingredient;
import com.reciper.repository.IngredientRepository;
import com.reciper.service.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class IngredientService extends AbstractService<Ingredient, IngredientRepository> {
    public IngredientService(IngredientRepository ingredientRepository) {
        super(ingredientRepository);
    }
}
