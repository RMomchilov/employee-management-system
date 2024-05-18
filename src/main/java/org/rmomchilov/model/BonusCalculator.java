package org.rmomchilov.model;

import java.math.BigDecimal;

public interface BonusCalculator {

    BigDecimal calculateBonus(Employee employee);
}
