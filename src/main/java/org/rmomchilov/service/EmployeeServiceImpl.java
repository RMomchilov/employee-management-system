package org.rmomchilov.service;

import org.rmomchilov.model.DepartmentBonus;
import org.rmomchilov.model.Employee;
import org.rmomchilov.model.PerformanceBonus;
import org.rmomchilov.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Long addEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee).getId();
    }

    @Override
    public void removeEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Integer calculateBonus(Employee employee) {
        BigDecimal departmentBonus = (new DepartmentBonus()).calculateBonus(employee);
        BigDecimal performanceBonus = (new PerformanceBonus(new BigDecimal("0.1"))).calculateBonus(employee);
        return departmentBonus.add(performanceBonus)
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();
    }
}
