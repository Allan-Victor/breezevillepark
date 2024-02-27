package com.breezevillepark.Kitchen.and.Bar.System.recipecategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeCategoryRepoImpl {
    private final RecipeCategoryRepository recipeCategoryRepository;

    @Autowired
    public RecipeCategoryRepoImpl(RecipeCategoryRepository recipeCategoryRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
    }
}
