package ru.gorbachev.calculator.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gorbachev.calculator.service.PaymentsCalculatorService;
import ru.gorbachev.calculator.service.PaymentsCalculatorServiceImpl;

import java.time.LocalDate;

@RestController
@RequestMapping
public class PaymentsCalculatorController {
    private final PaymentsCalculatorService service = new PaymentsCalculatorServiceImpl();

    @GetMapping("/calculate")
    String calculate(@RequestParam double salary, @RequestParam int days,
                     @RequestParam(required = false)
                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start) {
        return service.getPayment(salary, days, start);
    }
}
