package org.rmomchilov.model;

import org.rmomchilov.model.enums.DepartmentEnum;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DepartmentBonus implements BonusCalculator {

    private static final Map<DepartmentEnum, BigDecimal> scorecard = new HashMap<>();

    public DepartmentBonus() {
        if (scorecard.isEmpty()) {
            scorecard.put(DepartmentEnum.ENGINEERING, new BigDecimal("0.1"));
            scorecard.put(DepartmentEnum.HR, new BigDecimal("0.08"));
            scorecard.put(DepartmentEnum.MARKETING, new BigDecimal("0.12"));
            scorecard.put(DepartmentEnum.SALES, new BigDecimal("0.15"));
            scorecard.put(DepartmentEnum.IT, new BigDecimal("0.09"));
        }
    }

    @Override
    public BigDecimal calculateBonus(Employee employee) {
        return scorecard.get(employee.getDepartment())
                .multiply(BigDecimal.valueOf(employee.getSalary()));
    }
}
