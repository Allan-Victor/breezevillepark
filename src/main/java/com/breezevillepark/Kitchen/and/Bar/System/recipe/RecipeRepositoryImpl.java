package com.breezevillepark.Kitchen.and.Bar.System.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeRepositoryImpl {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeRepositoryImpl( @Lazy RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe saveRecipe(Recipe recipe) {
        return  recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe editRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }
}
