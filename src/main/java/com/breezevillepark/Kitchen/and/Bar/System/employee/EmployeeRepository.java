package com.breezevillepark.Kitchen.and.Bar.System.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query
    boolean existsEmployeeByUserName(String userName);
    boolean existsByUserNameAndEmployeeId(String userName, Integer id);

    @Query("select c from employee c " +
            "where lower(c.firstName) like lower(concat('%', :filterName, '%')) " +
            "or lower(c.surName) like lower(concat('%', :filterName, '%'))")
    List<Employee> getEmployeeByName(@Param("filterName") String filterName);

}
