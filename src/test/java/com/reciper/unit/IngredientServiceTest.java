package com.reciper.unit;

import com.reciper.entity.Ingredient;
import com.reciper.repository.IngredientRepository;
import com.reciper.service.IngredientService;
import com.reciper.service.IngredientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class IngredientServiceTest {
    @MockBean
    IngredientRepository ingredientRepository;

    private IngredientService ingredientService;

    @Before
    public void before() {
        ingredientService = new IngredientServiceImpl(ingredientRepository);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreate() {
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(new Ingredient());

        ingredientService.create(new Ingredient());
        verify(ingredientRepository, times(1)).save(any(Ingredient.class));
    }

    @Test
    public void testUpdate() {
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(new Ingredient());

        ingredientService.update(new Ingredient());
        verify(ingredientRepository, times(1)).save(any(Ingredient.class));
    }

    @Test
    public void testSelect() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        ingredients.add(new Ingredient());
        ingredients.add(new Ingredient());

        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(new Ingredient());
        ingredientService.create(ingredients.get(0));
        ingredientService.create(ingredients.get(1));
        ingredientService.create(ingredients.get(2));

        verify(ingredientRepository, times(3)).save(any(Ingredient.class));
        when(ingredientRepository.findAll()).thenReturn(ingredients);
        ingredientService.findAll();

        verify(ingredientRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        ingredientService.delete(1);
        verify(ingredientRepository, times(1)).deleteById(anyInt());
    }
}
