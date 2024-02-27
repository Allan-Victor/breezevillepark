package com.breezevillepark.Kitchen.and.Bar.System.views;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.employee.EmployeeService;
import com.breezevillepark.Kitchen.and.Bar.System.employee.Gender;
import com.breezevillepark.Kitchen.and.Bar.System.exceptions.DuplicateResourceException;
import com.breezevillepark.Kitchen.and.Bar.System.role.RoleName;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;




@Route(value = "/add employee", layout = MainLayout.class)
public class EmployeeForm extends EmployeeFormConfiguration {

    private final EmployeeService service;
    private final EmployeeListView employeeListView;

    Binder<Employee> binder = new BeanValidationBinder<>(Employee.class); //Bind

    private Employee employee = new Employee();

    public EmployeeForm(EmployeeService service) {
        this.service = service;
        employeeListView = new EmployeeListView(service);


        //Form Header
        H2 empFormHeading = new H2("Employee Registration Form");
        empFormHeading.addClassName("empFormHeading");
        empFormHeading.setSizeFull();
        HorizontalLayout heading = new HorizontalLayout(empFormHeading);

        heading.setJustifyContentMode(JustifyContentMode.CENTER);
        heading.setPadding(true);
        heading.setMargin(true);

        add(empFormHeading,configureFormLayout(),createButtonsLayout());
    }

    protected Component configureFormLayout(){
        return EmployeeFormConfiguration.configureEmployeeFormLayout(binder, employee);
    }


    private Component createButtonsLayout() {
        Button save = new Button("Save");

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        //Listeners
        save.addClickListener(buttonClickEvent -> EmployeeFormConfiguration.validateAndSave(binder,service, employeeListView, employee));

        HorizontalLayout buttonLayout = new HorizontalLayout(save);
        buttonLayout.setAlignItems(Alignment.START);
        return buttonLayout;
    }

    private void setEmployee(Employee employee) {
        this.employee = employee;
        binder.readBean(employee);


    }






    // Events
    public static abstract class EmployeeFormEvent extends ComponentEvent<EmployeeForm> {
        private final Employee  employee;

        protected EmployeeFormEvent(EmployeeForm source, Employee employee) { // (1)
            super(source, false);
            this.employee = employee;
        }

        public Employee getEmployee() {
            return employee;
        }
    }

    public static class SaveEvent extends EmployeeFormEvent {
        SaveEvent(EmployeeForm source, Employee employee) {
            super(source, employee);

        }
    }

    public static class DeleteEvent extends EmployeeFormEvent {
        DeleteEvent(EmployeeForm source, Employee employee) {
            super(source, employee);
        }

    }

    public static class CloseEvent extends EmployeeFormEvent {
        CloseEvent(EmployeeForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}


