package com.breezevillepark.Kitchen.and.Bar.System.recipematerials;

import com.breezevillepark.Kitchen.and.Bar.System.material.Material;
import com.breezevillepark.Kitchen.and.Bar.System.recipe.Recipe;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RecipeMaterials {
    private String comment;
    private double cost;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Id
    @SequenceGenerator(
            name = "recipe_material_id_sequence",
            sequenceName = "recipe_material_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_material_id_sequence"
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private int recipeMaterialId;
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


}
