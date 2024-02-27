package com.breezevillepark.Kitchen.and.Bar.System.views;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.material.InventoryService;
import com.breezevillepark.Kitchen.and.Bar.System.material.Material;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "/inventory", layout = MainLayout.class)
@PageTitle("Inventory | BreezeVillePark")
public class InventoryListView extends VerticalLayout {
    private final InventoryService inventoryService;
    Grid<Material> inventoryGrid = new Grid<>(Material.class);
    private  final EditInventoryView editInventoryView;

    public InventoryListView(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.editInventoryView = new EditInventoryView(inventoryService, this);

        addClassName("inventoryList");
        setSizeFull();
        configureInventoryGrid();
        updateInventoryList();

        add(configureTitle(), configureToolBar(),inventoryGrid);
    }

    private Component configureToolBar() {
        Button createNew = new Button("Create New");

        createNew.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(InventoryForm.class));
        return new HorizontalLayout(createNew);
    }

    private H2 configureTitle() {
        return new H2("Inventory");
    }

    private void configureInventoryGrid() {
        inventoryGrid.addClassName("inventoryGrid");
        inventoryGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);


        //add Columns for primitive data types in the model
        inventoryGrid.setColumns("name", "quantity","unitOfMeasure","costPerUnit");

        inventoryGrid.getColumns().forEach(materialColumn -> materialColumn.setAutoWidth(true));

        //Edit column
        inventoryGrid.asSingleSelect().addValueChangeListener(e -> editMaterial(e.getValue()));
    }

    public void updateInventoryList() {
        inventoryGrid.setItems(inventoryService.showAllMaterials());
    }
    private void editMaterial(Material selectedMaterial) {
        if (selectedMaterial == null){
            closeEditor();
        }else {
            editInventoryView.setMaterial(selectedMaterial); ;
            editInventoryView.showDialog();
            addClassName("editing");

        }
    }
    private void closeEditor() {
        this.editInventoryView.setMaterial(new Material());
        this.editInventoryView.setVisible(false);
        removeClassName("editing");
    }

}
