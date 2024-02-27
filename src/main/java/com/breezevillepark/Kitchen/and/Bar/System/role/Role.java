package com.breezevillepark.Kitchen.and.Bar.System.role;

import com.breezevillepark.Kitchen.and.Bar.System.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Role {
    @ManyToMany(mappedBy = "roles")
    private List<Employee> members;

    @Id
    @SequenceGenerator(
            name = "role_id_sequence",
            sequenceName = "role_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_id_sequence"
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
