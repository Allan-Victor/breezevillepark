package com.breezevillepark.Kitchen.and.Bar.System.employee;


import com.breezevillepark.Kitchen.and.Bar.System.exceptions.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepositoryImpl employeeDao;


    @Autowired
    public EmployeeService(EmployeeRepositoryImpl employeeDao) {
        this.employeeDao = employeeDao;

    }

    public Employee addNewEmployee(Employee employee){
        //check if employee null
        //check if employee username exists
        if (employee == null){
            throw new IllegalArgumentException("Employee cannot be Null");
        }else if (employeeDao.existsEmployeeWithUserName(employee.getUserName())){
            throw new DuplicateResourceException("Employee with username exists");
        }
        //add employee
        return employeeDao.saveEmployee(employee);
    }

    public List<Employee> showAllEmployees(){
        return employeeDao.getAllEmployees();
    }

    public Employee updateEmployee(Employee employee){
        if (!employeeDao.existsWithUsernameAndId(employee.getUserName(), employee.getEmployeeId())){
            System.out.println("This user does not exist");
        }
        return employeeDao.editEmployee(employee);

    }
    public void deleteEmployee(Employee employee){
        employeeDao.removeEmployee(employee);

    }

    public void deleteEmployeeById(Integer id) {
        employeeDao.removeEmployeeById(id);
    }

    //Search Employee by name
    public List<Employee> searchEmployeeByName(String employeeFilter) {
        if (employeeFilter == null || employeeFilter.isEmpty()){
            employeeDao.getAllEmployees();
        }
        return employeeDao.getEmployeeByName(employeeFilter);
    }

}
