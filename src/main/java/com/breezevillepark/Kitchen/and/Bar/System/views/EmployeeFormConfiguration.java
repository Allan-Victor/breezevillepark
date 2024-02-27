package com.breezevillepark.Kitchen.and.Bar.System.views;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import com.breezevillepark.Kitchen.and.Bar.System.employee.EmployeeService;
import com.breezevillepark.Kitchen.and.Bar.System.employee.Gender;
import com.breezevillepark.Kitchen.and.Bar.System.exceptions.DuplicateResourceException;
import com.breezevillepark.Kitchen.and.Bar.System.role.RoleName;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

public abstract class EmployeeFormConfiguration extends VerticalLayout {

    private static Employee employee;
    private static Logger log;

    public static FormLayout configureEmployeeFormLayout(Binder<Employee> binder, Employee employee){
        FormLayout employeeForm = new FormLayout();

        TextField firstName = createTextField("First Name", "First Name");
        TextField middleName = createTextField("Middle Name", "Middle Name");
        TextField surName = createTextField("Surname", "Enter Surname");
        IntegerField age = createIntegerField("Age", "Age");
        TextField userName = createTextFieldWithPrefix("User Name", "Enter UserName", VaadinIcon.USER.create(), 20);

        List<Gender> genders = Arrays.asList(Gender.values());
        List<RoleName> roleNames = Arrays.asList(RoleName.values());

        ComboBox<Gender> gender = createComboBox("Gender", genders, Gender::toString);
        ComboBox<RoleName> roleName = createComboBox("Role", roleNames, RoleName::toString);

        binder.forField(firstName).bind(Employee::getFirstName, Employee::setFirstName);
        binder.forField(middleName).bind(Employee::getMiddleName, Employee::setMiddleName);
        binder.forField(surName).bind(Employee::getSurName, Employee::setSurName);
        binder.forField(age).bind(Employee::getAge, Employee::setAge);
        binder.forField(userName).bind(Employee::getUserName, Employee::setUserName);
        binder.forField(gender).bind(Employee::getGender, Employee::setGender);
        binder.forField(roleName).bind(Employee::getRoleName, Employee::setRoleName);

        binder.readBean(employee);

        employeeForm.add(
                firstName, middleName, surName, age, userName, gender, roleName
        );

        employeeForm.setSizeFull();
        return employeeForm;
        
    }

    private static TextField createTextFieldWithPrefix(String label, String placeholder, Icon icon, int maxLength) {
        TextField textField = createTextField(label, placeholder);
        textField.setPrefixComponent(icon);
        textField.setMaxLength(maxLength);
        return textField;
    }

    private static IntegerField createIntegerField(String label, String placeholder) {
        IntegerField integerField = new IntegerField(label);
        integerField.setRequiredIndicatorVisible(true);
        integerField.setPlaceholder(placeholder);
        return integerField;
    }

    private static TextField createTextField(String label, String placeholder) {
        TextField textField = new TextField(label);
        textField.setRequiredIndicatorVisible(true);
        textField.addThemeVariants(TextFieldVariant.MATERIAL_ALWAYS_FLOAT_LABEL);
        textField.setPlaceholder(placeholder);
        return textField;
    }
    private static <T> ComboBox<T> createComboBox(String label, List<T> items, ItemLabelGenerator<T> labelGenerator) {
        ComboBox<T> comboBox = new ComboBox<>(label);
        comboBox.setItems(items);
        comboBox.setItemLabelGenerator(labelGenerator);
        comboBox.setSizeFull();
        return comboBox;
    }

    static void validateAndSave(Binder<Employee> binder, EmployeeService service, EmployeeListView employeeListView, Employee employee) {
            try {
                binder.writeBean(employee);
                service.addNewEmployee(employee);
                employeeListView.updateList();
                clearForm(binder, new Employee());
                Notification.show("Successfully added employee", 5000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (ValidationException e) {
                Notification.show("Please enter all the details");
                log.error("ValidationException during employee save", e);
            } catch (DuplicateResourceException e) {
                Notification.show("Employee with username '" + employee.getUserName() + "' already exists", 5000, Notification.Position.TOP_END)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);

            }
        }

    static void updateEmployee(Binder<Employee> binder, EmployeeService service, EmployeeListView employeeListView, Employee employee, Dialog dialog) {
        try {
            binder.writeBean(employee);
            service.updateEmployee(employee);
            employeeListView.updateList();
            clearForm(binder, new Employee());
            dialog.close();
            Notification.show(" Employee Successfully updated", 5000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } catch (ValidationException e) {
            Notification.show("Please enter all the details");
            log.error("ValidationException during employee save", e);
        } catch (DuplicateResourceException e) {
            Notification.show("Employee with username '" + employee.getUserName() + "' already exists", 5000, Notification.Position.TOP_END)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);

        }
    }

    static void deleteEmployee(EmployeeService service, EmployeeListView employeeListView, Employee employee, Dialog dialog){
        service.deleteEmployee(employee);
        employeeListView.updateList();
        Notification.show("Employee + " + employee.getFirstName() + " " + employee.getSurName() + " has been deleted", 4000, Notification.Position.TOP_END ).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        dialog.close();
    }



    private static void clearForm(Binder<Employee> binder, Employee emptyEmployee) {
        if (binder != null && emptyEmployee != null) {
            binder.readBean(emptyEmployee);
        } else {
            System.out.println("Cannot be Null");
        }
    }




}
