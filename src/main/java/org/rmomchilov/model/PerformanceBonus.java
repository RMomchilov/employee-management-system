package org.rmomchilov.model;

import java.math.BigDecimal;

public class PerformanceBonus implements BonusCalculator {

    private final BigDecimal COEFFICIENT;

    public PerformanceBonus(BigDecimal coefficient) {
        this.COEFFICIENT = coefficient;
    }

    @Override
    public BigDecimal calculateBonus(Employee employee) {
        return COEFFICIENT.multiply(BigDecimal.valueOf(employee.getSalary()));
    }
}
