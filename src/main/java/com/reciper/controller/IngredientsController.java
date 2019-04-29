package com.reciper.controller;

import com.reciper.controller.base.AbstractController;
import com.reciper.entity.Ingredient;
import com.reciper.service.ingredient.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController extends AbstractController<Ingredient, IngredientService> {
    protected IngredientsController(IngredientService service) {
        super(service);
    }
}
