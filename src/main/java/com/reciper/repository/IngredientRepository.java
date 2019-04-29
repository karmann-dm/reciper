package com.reciper.repository;

import com.reciper.entity.Ingredient;
import com.reciper.repository.base.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CommonRepository<Ingredient> {
}
