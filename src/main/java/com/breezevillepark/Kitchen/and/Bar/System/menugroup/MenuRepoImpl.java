package com.breezevillepark.Kitchen.and.Bar.System.menugroup;


import com.breezevillepark.Kitchen.and.Bar.System.exceptions.DuplicateResourceException;
import com.breezevillepark.Kitchen.and.Bar.System.exceptions.MenuGroupNotFoundException;
import com.breezevillepark.Kitchen.and.Bar.System.recipe.Recipe;
import com.breezevillepark.Kitchen.and.Bar.System.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepoImpl {
    private final MenuRepository menuRepository;
    private final RecipeRepository recipeRepository;
    private  MenuGroup menuGroup;

    @Autowired
    public MenuRepoImpl(MenuRepository menuRepository, RecipeRepository recipeRepository) {
        this.menuRepository = menuRepository;
        this.recipeRepository = recipeRepository;

    }

    public MenuGroup saveMenuGroup(MenuGroup menuGroup) {
        return menuRepository.save(menuGroup);
    }

    public List<MenuGroup> getAllMenuGroups() {
        return menuRepository.findAll();
    }

    public MenuGroup editMenuGroup(MenuGroup menuGroup) {
        return menuRepository.save(menuGroup);
    }

    public void removeMenuGroup(MenuGroup menuGroup) {
        menuRepository.delete(menuGroup);
    }

    public List<Recipe> getAvailableRecipes() {
        return menuGroup.getRecipes();

    }



    public void removeRecipeInMenuGroup(int menuGroupId, Recipe recipe) {
        if (!menuRepository.existsById(menuGroupId)){
            throw new MenuGroupNotFoundException("Menu Group does not exist");
        }else{
            menuGroup.getRecipes().remove(recipe);
            recipeRepository.delete(recipe);
            menuRepository.save(menuGroup);
        }

    }

    public void saveRecipeToMenuGroup(int menuGroupId, Recipe recipe) {
        menuRepository.findById(menuGroupId).orElseThrow(
                () -> new MenuGroupNotFoundException("MenuGroup Not Found"));
        recipe.setMenuGroup(menuGroup);
        menuGroup.getRecipes().add(recipe);
        menuRepository.save(this.menuGroup);
    }
}
