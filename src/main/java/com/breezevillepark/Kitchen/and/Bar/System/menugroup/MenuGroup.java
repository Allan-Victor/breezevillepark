package com.breezevillepark.Kitchen.and.Bar.System.menugroup;

import com.breezevillepark.Kitchen.and.Bar.System.recipe.Recipe;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class MenuGroup {
    private boolean isActive;
    private LocalDate availableDay;
    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;
    private String description;
    private String groupTitle;
    private boolean isAvailableNow;

    @Id
    @SequenceGenerator(
            name = "menu_group_id_sequence",
            sequenceName = "menu_group_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "menu_group_id_sequence"
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private int menuGroupId;

    @OneToMany(mappedBy = "menuGroup")
    private List<Recipe> recipes;


    public MenuGroup() {
    }

    public MenuGroup(boolean isActive, LocalDate availableDay,
                     LocalDateTime availableFrom, LocalDateTime availableTo,
                     String description, String groupTitle,
                     boolean isAvailableNow, int menuGroupId,
                     List<Recipe> recipes) {
        this.isActive = isActive;
        this.availableDay = availableDay;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.description = description;
        this.groupTitle = groupTitle;
        this.isAvailableNow = isAvailableNow;
        this.menuGroupId = menuGroupId;
        this.recipes = recipes;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getAvailableDay() {
        return availableDay;
    }

    public void setAvailableDay(LocalDate availableDay) {
        this.availableDay = availableDay;
    }

    public LocalDateTime getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDateTime availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDateTime getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalDateTime availableTo) {
        this.availableTo = availableTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public boolean isAvailableNow() {
        return isAvailableNow;
    }

    public void setAvailableNow(boolean availableNow) {
        isAvailableNow = availableNow;
    }

    public int getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(int menuGroupId) {
        this.menuGroupId = menuGroupId;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuGroup menuGroup = (MenuGroup) o;
        return isActive == menuGroup.isActive && isAvailableNow == menuGroup.isAvailableNow && menuGroupId == menuGroup.menuGroupId && Objects.equals(availableDay, menuGroup.availableDay) && Objects.equals(availableFrom, menuGroup.availableFrom) && Objects.equals(availableTo, menuGroup.availableTo) && Objects.equals(description, menuGroup.description) && Objects.equals(groupTitle, menuGroup.groupTitle) && Objects.equals(recipes, menuGroup.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActive, availableDay, availableFrom, availableTo, description, groupTitle, isAvailableNow, menuGroupId, recipes);
    }

    @Override
    public String toString() {
        return "MenuGroup{" +
                "isActive=" + isActive +
                ", availableDay=" + availableDay +
                ", availableFrom=" + availableFrom +
                ", availableTo=" + availableTo +
                ", description='" + description + '\'' +
                ", groupTitle='" + groupTitle + '\'' +
                ", isAvailableNow=" + isAvailableNow +
                ", menuGroupId=" + menuGroupId +
                ", recipes=" + recipes +
                '}';
    }
}
