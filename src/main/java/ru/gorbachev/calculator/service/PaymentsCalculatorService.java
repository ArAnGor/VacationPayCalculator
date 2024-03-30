package ru.gorbachev.calculator.service;

public interface PaymentsCalculatorService {
    String getPayment(double averageSalaryPerMonth, int numberOfDays);
}
