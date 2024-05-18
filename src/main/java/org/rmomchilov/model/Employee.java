package org.rmomchilov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.rmomchilov.model.enums.DepartmentEnum;

@Entity
public class Employee {

    @Id
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private DepartmentEnum department;
    private Integer salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentEnum getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEnum department) {
        this.department = department;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
