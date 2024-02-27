package com.breezevillepark.Kitchen.and.Bar.System.recipecategory;

import com.breezevillepark.Kitchen.and.Bar.System.recipe.Recipe;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class RecipeCategory {
    private String RecipeCategoryName;

    @Id
    @SequenceGenerator(
            name = "recipe_category_id_sequence",
            sequenceName = "recipe_category_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_category_id_sequence"
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private int recipeCategoryId;

    @OneToMany(mappedBy = "recipeCategory")
    private List<Recipe> recipeList;
}
