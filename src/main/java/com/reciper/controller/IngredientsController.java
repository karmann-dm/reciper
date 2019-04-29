package com.reciper.controller;

import com.reciper.entity.Ingredient;
import com.reciper.service.ingredient.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    private final IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("")
    public ResponseEntity<Ingredient> createIngredient(@RequestBody @Valid Ingredient ingredient) {
        Ingredient saved = ingredientService.save(ingredient);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Integer id) {
        Ingredient retrieved = ingredientService.findById(id);
        return new ResponseEntity<>(retrieved, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Collection<Ingredient>> getIngredients() {
        Collection<Ingredient> ingredients = ingredientService.findAll();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Integer id, @RequestBody @Valid Ingredient ingredient) {
        ingredient.setId(id);
        Ingredient updated = ingredientService.update(ingredient);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteIngredient(@PathVariable Integer id) {
        ingredientService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
