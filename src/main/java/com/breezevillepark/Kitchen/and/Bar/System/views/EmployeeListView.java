package com.breezevillepark.Kitchen.and.Bar.System.views;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.employee.EmployeeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */



@Route(value = "", layout = MainLayout.class)
@PageTitle("Employees | BreezeVillePark")
public class EmployeeListView extends VerticalLayout {
    private final EmployeeService employeeService;
    private final Grid<Employee> employeeGrid = new Grid<>(Employee.class);
    private final TextField filterEmployee = new TextField();
    private  final EditEmployeeView editEmployeeView;


    public EmployeeListView(EmployeeService employeeService) {
        this.employeeService = employeeService;
        editEmployeeView = new EditEmployeeView(employeeService, this);
        // Use custom CSS classes to apply styling. This is defined in
        // styles.css.
        addClassName("employee-list");
        setSizeFull();
        configureGrid();
        updateList();
        add(getToolBar(), employeeGrid);
    }


    private Component getToolBar() {
        filterEmployee.setPlaceholder("Filter by name");
        filterEmployee.setClearButtonVisible(true);// To clear what is in the text field
        filterEmployee.setValueChangeMode(ValueChangeMode.LAZY);// to limit hits on db. waits for user to stop typing
        filterEmployee.addValueChangeListener(e -> searchList());// update list when user is not typing in search filter

        Button addEmployeeBtn = new Button("Add Employee");

        addEmployeeBtn.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(EmployeeForm.class));

        //Horizontal Layout
        HorizontalLayout toolbar = new HorizontalLayout(filterEmployee, addEmployeeBtn);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void searchList() {
        employeeGrid.setItems(employeeService.searchEmployeeByName(filterEmployee.getValue()));
    }


    public void updateList() {
        employeeGrid.setItems(employeeService.showAllEmployees());// searches all contacts in the db that matches that filter value
    }

    private void configureGrid() {
        employeeGrid.addClassName("employee-grid");
        employeeGrid.setSizeFull();

        //For columns with primitive data types as in the employee model
        employeeGrid.setColumns("firstName", "surName");

        //adding columns that are of object type
        employeeGrid.addColumn(Employee::getGender).setHeader("Gender").isSortable();
        employeeGrid.addColumn(Employee::getRoleName).setHeader("Role").isSortable();
        employeeGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        employeeGrid.getColumns().forEach(employeeColumn -> employeeColumn.setAutoWidth(true));

        //Edit column
        employeeGrid.asSingleSelect().addValueChangeListener(e -> editEmployee(e.getValue()));

    }

    private void editEmployee(Employee selectedEmployee) {
        if (selectedEmployee == null){
          closeEditor();
        }else {
             editEmployeeView.setEmployee(selectedEmployee);
             editEmployeeView.showDialog();
             addClassName("editing");

        }
    }

    private void closeEditor() {
        this.editEmployeeView.setEmployee(new Employee());
        this.editEmployeeView.setVisible(false);
        removeClassName("editing");
    }


}

