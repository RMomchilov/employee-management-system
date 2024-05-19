package org.rmomchilov.service;

import org.rmomchilov.model.DepartmentBonus;
import org.rmomchilov.model.Employee;
import org.rmomchilov.model.PerformanceBonus;
import org.rmomchilov.model.exceptions.EmployeeExistsException;
import org.rmomchilov.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());
    private static final BigDecimal PERFORMANCE_COEFFICIENT = new BigDecimal("0.1");

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Long id) {
        logger.info("Fetching employee with id: {}", id);
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Long addEmployee(Employee employee) {
        logger.info("Saving new employee");
        if (employee.getId() != null) {
            logger.error("Employee already exists in the database");
            throw new EmployeeExistsException(employee.getId());
        }

        return employeeRepository.saveAndFlush(employee).getId();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        logger.info("Updating employee with id: {}", employee.getId());
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void removeEmployee(Long id) {
        logger.info("Deleting employee with id: {}", id);
        employeeRepository.deleteById(id);
    }

    @Override
    public Integer calculateBonus(Employee employee) {
        logger.info("Calculating bonuses for employee with id: {}", employee.getId());
        BigDecimal departmentBonus = (new DepartmentBonus()).calculateBonus(employee);
        BigDecimal performanceBonus = (new PerformanceBonus(PERFORMANCE_COEFFICIENT)).calculateBonus(employee);
        return departmentBonus.add(performanceBonus)
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();
    }
}
