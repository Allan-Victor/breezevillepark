package com.breezevillepark.Kitchen.and.Bar.System.recipecategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeCategoryService {
    private final RecipeCategoryRepoImpl recipeCategoryDao;

    @Autowired
    public RecipeCategoryService(RecipeCategoryRepoImpl recipeCategoryDao) {
        this.recipeCategoryDao = recipeCategoryDao;
    }
}
