package com.breezevillepark.Kitchen.and.Bar.System.employee;

import java.util.List;

public interface EmployeeRepoCustom {
    List<Employee> getAllEmployees();
    boolean existsEmployeeWithUserName(String userName);
    Employee saveEmployee(Employee employee);
    Employee editEmployee(Employee employee);
    void removeEmployeeById(Integer id);


}
