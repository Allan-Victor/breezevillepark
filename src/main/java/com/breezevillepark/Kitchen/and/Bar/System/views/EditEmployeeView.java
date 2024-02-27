package com.breezevillepark.Kitchen.and.Bar.System.views;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.employee.EmployeeService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;


public class EditEmployeeView extends EmployeeFormConfiguration {
    Binder<Employee> binder = new BeanValidationBinder<>(Employee.class);
    private final EmployeeService service;
    EmployeeListView employeeListView;
    private Employee  employee = new Employee();
    Dialog editEmployee = new Dialog();
    public EditEmployeeView(EmployeeService service, EmployeeListView employeeListView) {
        this.service = service;
        this.employeeListView = employeeListView;
        editEmployee.setHeaderTitle("Edit Employee Details");
        editEmployee.add(EmployeeFormConfiguration.configureEmployeeFormLayout(binder, employee));
        editEmployee.setWidth("40em");
        editEmployee.setHeight("30em");
        editEmployee.getFooter().add(createButtonsLayout());

    }
    public void showDialog(){
        editEmployee.open();
    }



    private Component createButtonsLayout() {
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button cancel = new Button("Cancel");

        edit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);//for showing error
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);


        //Listeners
        edit.addClickListener(buttonClickEvent -> EmployeeFormConfiguration.updateEmployee(binder,service, employeeListView, employee, editEmployee));
        delete.addClickListener( buttonClickEvent -> EmployeeFormConfiguration.deleteEmployee(service, employeeListView, employee, editEmployee));
        cancel.addClickListener(e -> editEmployee.close());



        HorizontalLayout buttonLayout = new HorizontalLayout(edit, delete, cancel);
        buttonLayout.setAlignItems(Alignment.START);
        return buttonLayout;
    }



    public void setEmployee(Employee employee) {
        this.employee = employee;
         binder.readBean(employee);
    }
}
