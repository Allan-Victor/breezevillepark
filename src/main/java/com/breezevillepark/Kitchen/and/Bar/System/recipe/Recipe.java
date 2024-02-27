package com.breezevillepark.Kitchen.and.Bar.System.recipe;

import com.breezevillepark.Kitchen.and.Bar.System.menugroup.MenuGroup;
import com.breezevillepark.Kitchen.and.Bar.System.orderitem.OrderItem;
import com.breezevillepark.Kitchen.and.Bar.System.recipecategory.RecipeCategory;
import com.breezevillepark.Kitchen.and.Bar.System.recipematerials.RecipeMaterials;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Recipe {
    private boolean canCook;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_group_id")
    private MenuGroup menuGroup;


    @OneToMany(mappedBy = "recipe")
    private List<OrderItem> orderItem;
    private double price;
    private Double profit;

    @ManyToOne
    @JoinColumn(name = "recipe_category_id")
    private RecipeCategory recipeCategory;


    @Id
    @SequenceGenerator(
            name = "recipe_id_sequence",
            sequenceName = "recipe_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_id_sequence"
    )
    @Column(name = "recipe_id",
            nullable = false,
            updatable = false
    )
    private int recipeId;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeMaterials> recipeMaterialsList;
    private int serving;
    private String title;
    private BigDecimal totalCost;
}
