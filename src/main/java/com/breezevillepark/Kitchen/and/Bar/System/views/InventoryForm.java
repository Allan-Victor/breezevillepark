package com.breezevillepark.Kitchen.and.Bar.System.views;

import com.breezevillepark.Kitchen.and.Bar.System.material.InventoryService;
import com.breezevillepark.Kitchen.and.Bar.System.material.Material;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route(value = "/add material", layout = MainLayout.class)
public class InventoryForm extends InventoryFormConfiguration{
    private final InventoryService inventoryService;
    Binder<Material> binder = new BeanValidationBinder<>(Material.class); //Bind

    private Material material = new Material();
    private InventoryListView inventoryListView;


    public InventoryForm(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        inventoryListView = new InventoryListView(inventoryService);


        H2 inventoryFormHeading = new H2("Inventory Registration Form");
        inventoryFormHeading.addClassName("inventoryFormHeading");
        inventoryFormHeading.setSizeFull();
        HorizontalLayout heading = new HorizontalLayout(inventoryFormHeading);

        heading.setJustifyContentMode(JustifyContentMode.CENTER);
        heading.setPadding(true);
        heading.setMargin(true);

        add(inventoryFormHeading,configureFormLayout(),createButtonsLayout());
    }

    private Component createButtonsLayout() {
        Button save = new Button("Save");

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);



        //Listeners
        save.addClickListener(buttonClickEvent -> InventoryFormConfiguration.validateAndSave(binder,inventoryService, inventoryListView, material));

        HorizontalLayout buttonLayout = new HorizontalLayout(save);
        buttonLayout.setAlignItems(Alignment.START);
        return buttonLayout;
    }

    private Component configureFormLayout() {
        return InventoryFormConfiguration.configureInventoryFormLayout(binder, material);
    }
}
