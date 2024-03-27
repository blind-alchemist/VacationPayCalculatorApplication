package ru.trofimova.vacationPayCalculator.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.trofimova.vacationPayCalculator.service.CalculatorService;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class CalculatorController {

    private CalculatorService calculatorService;


    @GetMapping
    public String calculate (@RequestParam double averageSalary, @RequestParam LocalDate firstDayOfVacation,
                             @RequestParam LocalDate lastDayOfVacation) {
        return calculatorService.vacationPay(averageSalary, firstDayOfVacation, lastDayOfVacation);
    }
}
