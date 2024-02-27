package com.breezevillepark.Kitchen.and.Bar.System.employee;

import com.breezevillepark.Kitchen.and.Bar.System.order.Order;
import com.breezevillepark.Kitchen.and.Bar.System.payment.Payment;
import com.breezevillepark.Kitchen.and.Bar.System.role.Role;
import com.breezevillepark.Kitchen.and.Bar.System.role.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


@Data
@Entity(name = "employee")
public class Employee {

    @Column(name = "age")
    private Integer age;

    private LocalDate dob;

    @Column(nullable = false,
            columnDefinition = "TEXT")
    private String firstName;

    @Column(columnDefinition = "TEXT")
    private String middleName;

    @Column(nullable = false,
            columnDefinition = "TEXT")
    private String surName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Id
    @SequenceGenerator(
            name = "employee_id_sequence",
            sequenceName = "employee_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_id_sequence"
    )
    @Column(name = "employee_id",
            nullable = false,
            updatable = false
    )
    private Integer employeeId;

    @Column(nullable = false,
            columnDefinition = "TEXT")
    private String userName;

    @OneToMany(mappedBy = "lastUpdatedEmployee")
    private  List<Order> orders;

    @OneToMany(mappedBy = "submittedEmployee")
    private List<Order> submittedOrders;

    @OneToMany(mappedBy = "employee")
    private List<Payment> payments;

    @ManyToMany
    @JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public void setFirstName(String firstName) {
        String firstLetter = firstName.substring(0,1).toUpperCase(Locale.ROOT);
        String otherLetters = firstName.substring(1);
        firstName = firstLetter + otherLetters;
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        String firstLetter = surName.substring(0,1).toUpperCase(Locale.ROOT);
        String otherLetters = surName.substring(1);
        surName = firstLetter + otherLetters;
        this.surName = surName;
    }

    public void setDob(LocalDate dob) {
        dob = LocalDate.now().minusYears(getAge());
        this.dob = dob;
    }
}
