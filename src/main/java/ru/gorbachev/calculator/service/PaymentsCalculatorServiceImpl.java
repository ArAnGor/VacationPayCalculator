package ru.gorbachev.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentsCalculatorServiceImpl implements PaymentsCalculatorService {
    @Override
    public String getPayment(double averageSalaryPerMonth, int numberOfDays) {
        double result = averageSalaryPerMonth / 29.3 * numberOfDays;
        return String.format("%.2f", result);
    }
}
