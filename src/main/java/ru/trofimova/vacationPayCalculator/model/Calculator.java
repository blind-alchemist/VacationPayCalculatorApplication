package ru.trofimova.vacationPayCalculator.model;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
public class Calculator {
    private double averageSalary;
    private LocalDate firstDayOfVacation;
    private LocalDate lastDayOfVacation;

}
