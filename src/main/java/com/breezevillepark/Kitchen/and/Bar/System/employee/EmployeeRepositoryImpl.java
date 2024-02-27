package com.breezevillepark.Kitchen.and.Bar.System.employee;

import com.breezevillepark.Kitchen.and.Bar.System.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class EmployeeRepositoryImpl implements EmployeeRepoCustom{
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryImpl(@Lazy EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Read
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    public boolean existsEmployeeWithUserName(String userName) {
        return employeeRepository.existsEmployeeByUserName(userName);

    }

    //Create and Edit
    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public Employee editEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public boolean existsWithUsernameAndId(String userName, Integer id){
        return employeeRepository.existsByUserNameAndEmployeeId(userName, id);
    }


    @Override
    public void removeEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    public void removeEmployee(Employee employee){
        employeeRepository.delete(employee);
    }

    public List<Employee> getEmployeeByName(String filterName) {
        return employeeRepository.getEmployeeByName(filterName);


    }
}
