package ru.gorbachev.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentsCalculatorServiceImpl implements PaymentsCalculatorService {
    @Override
    public String getPayment(double averageSalaryPerMonth, int numberOfDays) {
        double result = 0;
        if (averageSalaryPerMonth > 0 && numberOfDays > 0)
            result = averageSalaryPerMonth / 29.3 * numberOfDays;
        return String.format("%.2f", result);
    }
}
