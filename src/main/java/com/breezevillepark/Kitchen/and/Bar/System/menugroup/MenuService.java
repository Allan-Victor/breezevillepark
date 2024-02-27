package com.breezevillepark.Kitchen.and.Bar.System.menugroup;

import com.breezevillepark.Kitchen.and.Bar.System.exceptions.DuplicateResourceException;
import com.breezevillepark.Kitchen.and.Bar.System.recipe.Recipe;
import com.breezevillepark.Kitchen.and.Bar.System.recipe.RecipeRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepoImpl menuDao;
    private MenuGroup menuGroup;

    public MenuService(MenuRepoImpl menuDao) {
        this.menuDao = menuDao;

    }

    public MenuGroup addMenuGroup(MenuGroup menuGroup){
        if (menuGroup == null){
            throw new IllegalArgumentException("Menu Group cannot be null");
        }
        return menuDao.saveMenuGroup(menuGroup);
    }

    public List<MenuGroup> showAllMenuGroups(){
        return menuDao.getAllMenuGroups();
    }

    public MenuGroup updateMenuGroup(MenuGroup menuGroup){
        return menuDao.editMenuGroup(menuGroup);
    }

    public void deleteMenuGroup(MenuGroup menuGroup){
        menuDao.removeMenuGroup(menuGroup);
    }

    public List<Recipe> showAvailableRecipes(){
        return menuDao.getAvailableRecipes();
    }

    public void addRecipeToMenuGroup(int menuGroupId, Recipe recipe){
        menuDao.saveRecipeToMenuGroup(menuGroupId, recipe);
    }
    public void deleteRecipeInMenuGroup(int menuGroupId, Recipe recipe){
        menuDao.removeRecipeInMenuGroup(menuGroupId, recipe);
    }

}
