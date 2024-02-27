package com.breezevillepark.Kitchen.and.Bar.System.material;

import com.breezevillepark.Kitchen.and.Bar.System.recipematerials.RecipeMaterials;
import jakarta.persistence.*;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Material {
    private double costPerUnit;

    @Id
    @SequenceGenerator(
            name = "material_id_sequence",
            sequenceName = "material_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "material_id_sequence"
    )
    @Column(name = "material_id",
              nullable = false,
            updatable = false
    )
    private Integer materialId;

    private String name;
    private String nameWithUnit;
    private double quantity;

    @OneToMany(mappedBy = "material")
    private List<RecipeMaterials> recipeMaterialsList;
    private String unitOfMeasure;

    public Material() {
    }

    public Material(double costPerUnit, int materialId,
                    String name, String nameWithUnit,
                    double quantity, List<RecipeMaterials> recipeMaterialsList,
                    String unitOfMeasure) {
        this.costPerUnit = costPerUnit;
        this.materialId = materialId;
        this.name = name;
        this.nameWithUnit = nameWithUnit;
        this.quantity = quantity;
        this.recipeMaterialsList = recipeMaterialsList;
        this.unitOfMeasure = unitOfMeasure;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String firstLetter = name.substring(0,1).toUpperCase(Locale.ROOT);
        String otherLetters = name.substring(1);
        name = firstLetter + otherLetters;
        this.name = name;
    }

    public String getNameWithUnit() {
        return nameWithUnit;
    }

    public void setNameWithUnit(String nameWithUnit) {
        this.nameWithUnit = nameWithUnit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public List<RecipeMaterials> getRecipeMaterialsList() {
        return recipeMaterialsList;
    }

    public void setRecipeMaterialsList(List<RecipeMaterials> recipeMaterialsList) {
        this.recipeMaterialsList = recipeMaterialsList;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        String firstLetter = unitOfMeasure.substring(0,1).toUpperCase(Locale.ROOT);
        String otherLetters = unitOfMeasure.substring(1);
        unitOfMeasure = firstLetter + otherLetters;
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Double.compare(material.costPerUnit, costPerUnit) == 0 && Objects.equals(materialId, material.materialId) && Double.compare(material.quantity, quantity) == 0 && Objects.equals(name, material.name) && Objects.equals(nameWithUnit, material.nameWithUnit) && Objects.equals(recipeMaterialsList, material.recipeMaterialsList) && Objects.equals(unitOfMeasure, material.unitOfMeasure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(costPerUnit, materialId, name, nameWithUnit, quantity, recipeMaterialsList, unitOfMeasure);
    }

    @Override
    public String toString() {
        return "Material{" +
                "costPerUnit=" + costPerUnit +
                ", materialId=" + materialId +
                ", name='" + name + '\'' +
                ", nameWithUnit='" + nameWithUnit + '\'' +
                ", quantity=" + quantity +
                ", recipeMaterialsList=" + recipeMaterialsList +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                '}';
    }
}


