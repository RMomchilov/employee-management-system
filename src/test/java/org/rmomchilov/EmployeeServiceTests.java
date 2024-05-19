package org.rmomchilov;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rmomchilov.model.Employee;
import org.rmomchilov.model.enums.DepartmentEnum;
import org.rmomchilov.model.exceptions.EmployeeExistsException;
import org.rmomchilov.repository.EmployeeRepository;
import org.rmomchilov.service.EmployeeService;
import org.rmomchilov.service.EmployeeServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee();
        employee.setName("R Momchilov");
        employee.setSalary(2000);
        employee.setDepartment(DepartmentEnum.ENGINEERING);

        Employee employeeWithId = new Employee();
        employeeWithId.setId(1L);
        employeeWithId.setName("R Momchilov");
        employeeWithId.setSalary(2000);
        employeeWithId.setDepartment(DepartmentEnum.ENGINEERING);

        given(employeeRepository.saveAndFlush(employee))
                .willReturn(employeeWithId);

        Long employeeId = employeeService.addEmployee(employee);
        assertThat(employeeId).isEqualTo(employeeWithId.getId());
    }

    @Test
    public void testAddOldEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("R Momchilov");
        employee.setSalary(2000);
        employee.setDepartment(DepartmentEnum.ENGINEERING);

        assertThrows(EmployeeExistsException.class, () -> employeeService.addEmployee(employee));

        verify(employeeRepository, never()).saveAndFlush(any(Employee.class));
    }

    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("R Momchilov");
        employee.setSalary(2000);
        employee.setDepartment(DepartmentEnum.ENGINEERING);

        given(employeeRepository.findById(employee.getId())).
                willReturn(Optional.of(employee));

        Employee databaseEmployee = employeeService.getEmployee(employee.getId());
        assertThat(databaseEmployee).isEqualTo(employee);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("R Momchilov");
        employee.setSalary(2000);
        employee.setDepartment(DepartmentEnum.ENGINEERING);

        given(employeeRepository.saveAndFlush(employee))
                .willReturn(employee);

        employeeService.updateEmployee(employee);

        employee.setSalary(3000);
        Employee databaseEmployee = employeeService.updateEmployee(employee);
        assertThat(databaseEmployee).isEqualTo(employee);

        verify(employeeRepository, atLeastOnce()).saveAndFlush(any(Employee.class));
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("R Momchilov");
        employee.setSalary(2000);
        employee.setDepartment(DepartmentEnum.ENGINEERING);

        employeeService.removeEmployee(employee.getId());

        verify(employeeRepository, atLeastOnce()).deleteById(employee.getId());
    }
}
