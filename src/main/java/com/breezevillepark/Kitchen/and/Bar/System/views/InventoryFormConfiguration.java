package com.breezevillepark.Kitchen.and.Bar.System.views;



import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.employee.EmployeeService;
import com.breezevillepark.Kitchen.and.Bar.System.exceptions.DuplicateResourceException;
import com.breezevillepark.Kitchen.and.Bar.System.material.InventoryService;
import com.breezevillepark.Kitchen.and.Bar.System.material.Material;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import org.slf4j.Logger;


public class InventoryFormConfiguration extends VerticalLayout {
    private static Logger log;


    public static FormLayout configureInventoryFormLayout(Binder<Material> binder, Material material){
        FormLayout inventoryForm = new FormLayout();

        TextField name = createTextField("Name");
        TextField unitOfMeasure = createTextField("Unit of Measure");
        NumberField quantity = createNumberField("Quantity");
        NumberField costPerUnit = createNumberField("Cost Per Unit");



        binder.forField(name).bind(Material::getName, Material::setName);
        binder.forField(unitOfMeasure).bind(Material::getUnitOfMeasure, Material::setUnitOfMeasure);
        binder.forField(quantity).bind(Material::getQuantity, Material::setQuantity);
        binder.forField(costPerUnit).bind(Material::getCostPerUnit, Material::setCostPerUnit);


        binder.readBean(material);

        inventoryForm.add(name, unitOfMeasure, quantity, costPerUnit);

        inventoryForm.setSizeFull();
        return inventoryForm;

    }

    private static NumberField createNumberField(String label) {
        NumberField numberField = new NumberField(label);
        numberField.setRequiredIndicatorVisible(true);
        numberField.isRequired();
        return numberField;
    }

    private static TextField createTextField(String label) {
        TextField textField = new TextField(label);
        textField.setRequiredIndicatorVisible(true);
        textField.setRequired(textField.isRequired());
        textField.addThemeVariants(TextFieldVariant.MATERIAL_ALWAYS_FLOAT_LABEL);
        return textField;
    }

    static void validateAndSave(Binder<Material> binder, InventoryService inventoryService, InventoryListView inventoryListView, Material material) {
        try {
            if (!(binder == null)){
                binder.writeBean(material);
                inventoryService.addNewMaterial(material);
                inventoryListView.updateInventoryList();
                clearForm(binder, new Material());
                Notification.show("Successfully added material", 5000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }else {
                Notification.show("Please enter all the details, Can not be empty");
            }
        } catch (ValidationException e) {
            Notification.show("Please enter all the details");
            log.error("ValidationException during inventory save", e);
            setLog(log);
        } catch (DuplicateResourceException e) {
            Notification.show("Material '" + material.getName() + "' already exists", 5000, Notification.Position.TOP_END)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);

        }

    }

    static void updateMaterial(Binder<Material> binder, InventoryService inventoryService, InventoryListView inventoryListView, Material material, Dialog dialog) {
        try {
            binder.writeBean(material);
            inventoryService.updateMaterial(material);
            inventoryListView.updateInventoryList();
            clearForm(binder, new Material());
            dialog.close();
            Notification.show(" Successfully updated", 5000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } catch (ValidationException e) {
            Notification.show("Please enter all the details");
            log.error("ValidationException during material save", e);
        } catch (DuplicateResourceException e) {
            Notification.show("Material '" + material.getName() + "' already exists", 5000, Notification.Position.TOP_END)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);

        }catch (NullPointerException e){
            Notification.show("Please enter all the details, Can not be empty");
        }
    }

    static void deleteMaterial(InventoryService inventoryService, InventoryListView inventoryListView, Material material, Dialog dialog){
        inventoryService.deleteMaterial(material);
        inventoryListView.updateInventoryList();
        Notification.show("Material '" + material.getName() + " has been deleted", 4000, Notification.Position.TOP_END ).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        dialog.close();
    }


    private static void clearForm(Binder<Material> binder, Material emptyMaterial) {
        if (binder != null && emptyMaterial != null) {
            binder.readBean(emptyMaterial);
        } else {
            System.out.println("Cannot be Null");
        }
    }


    public static void setLog(Logger log) {
        InventoryFormConfiguration.log = log;
    }
}
