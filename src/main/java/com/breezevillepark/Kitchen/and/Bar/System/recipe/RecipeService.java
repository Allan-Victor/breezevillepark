package com.breezevillepark.Kitchen.and.Bar.System.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepositoryImpl recipeDao;

    @Autowired
    public RecipeService(RecipeRepositoryImpl recipeDao) {
        this.recipeDao = recipeDao;
    }
    //Manage Recipes

    public Recipe addNewRecipe(Recipe recipe){
        if (recipe == null){
            throw new IllegalArgumentException("Recipe cannot be null");
        }
        return recipeDao.saveRecipe(recipe);
    }

    public List<Recipe> showAllRecipes(){
        return recipeDao.getAllRecipes();
    }

    public Recipe updateRecipe(Recipe recipe){
        return recipeDao.editRecipe(recipe);
    }

    public void deleteRecipe(Recipe recipe){
        recipeDao.removeRecipe(recipe);
    }

}
