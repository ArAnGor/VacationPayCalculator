package ru.gorbachev.calculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentsCalculatorServiceImpl implements PaymentsCalculatorService {
    private static final VacationHandler vacationHandler = new VacationHandler();

    @Override
    public String getPayment(double averageSalaryPerMonth, int numberOfDays, LocalDate startDate) {
        double result = 0;
        if (averageSalaryPerMonth > 0 && numberOfDays > 0) {
            if (startDate != null)
                numberOfDays = vacationHandler.getNumberOfWorkDays(startDate, numberOfDays);
            result = averageSalaryPerMonth / 29.3 * numberOfDays;
        }
        return String.format("%.2f", result);
    }
}
