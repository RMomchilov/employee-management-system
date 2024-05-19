package org.rmomchilov.service;

import org.rmomchilov.model.Employee;

public interface EmployeeService {

    Employee getEmployee(Long id);
    Long addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);
    void removeEmployee(Long id);
    Integer calculateBonus(Employee employee);
}
