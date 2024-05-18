package org.rmomchilov.service;

import org.rmomchilov.model.Employee;

public interface EmployeeService {

    Employee getEmployee(Long id);
    void updateEmployee(Employee employee);
    Long addEmployee(Employee employee);
    void removeEmployee(Long id);
    Integer calculateBonus(Employee employee);
}
