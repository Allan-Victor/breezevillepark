package com.breezevillepark.Kitchen.and.Bar.System.views;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.material.InventoryService;
import com.breezevillepark.Kitchen.and.Bar.System.material.Material;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class EditInventoryView extends InventoryFormConfiguration{
    Binder<Material> binder = new BeanValidationBinder<>(Material.class);
    private final InventoryService inventoryService;
    InventoryListView inventoryListView;
    private Material  material = new Material();
    Dialog editInventory = new Dialog();

    public EditInventoryView(InventoryService inventoryService,InventoryListView inventoryListView ) {
        this.inventoryService = inventoryService;
        this.inventoryListView = inventoryListView;
        editInventory.setHeaderTitle("Edit Material Details");
        editInventory.add(InventoryFormConfiguration.configureInventoryFormLayout(binder, material));
        editInventory.setWidth("40em");
        editInventory.setHeight("30em");
        editInventory.getFooter().add(createButtonsLayout());
    }

    private Component createButtonsLayout() {
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button cancel = new Button("Cancel");

        edit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);//for showing error
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);


        //Listeners
        edit.addClickListener(buttonClickEvent -> InventoryFormConfiguration.updateMaterial(binder,inventoryService, inventoryListView, material, editInventory));
        delete.addClickListener( buttonClickEvent -> InventoryFormConfiguration.deleteMaterial(inventoryService, inventoryListView, material, editInventory));
        cancel.addClickListener(e -> editInventory.close());



        HorizontalLayout buttonLayout = new HorizontalLayout(edit, delete, cancel);
        buttonLayout.setAlignItems(Alignment.START);
        return buttonLayout;
    }

    public void setMaterial(Material material) {
        this.material = material;
        binder.readBean(material);
    }

    public void showDialog(){
        editInventory.open();
    }
}
