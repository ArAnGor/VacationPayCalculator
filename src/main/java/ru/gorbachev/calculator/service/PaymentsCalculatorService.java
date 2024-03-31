package ru.gorbachev.calculator.service;

import java.time.LocalDate;

public interface PaymentsCalculatorService {
    String getPayment(double averageSalaryPerMonth, int numberOfDays, LocalDate startDate);
}
