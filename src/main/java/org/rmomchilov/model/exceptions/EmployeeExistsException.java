package org.rmomchilov.model.exceptions;

public class EmployeeExistsException extends RuntimeException {

    private final Long employeeId;

    public EmployeeExistsException(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}
