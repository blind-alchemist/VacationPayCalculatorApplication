package ru.trofimova.vacationPayCalculator.service;

import java.time.LocalDate;

public interface CalculatorService {
    String vacationPay (double averageSalary, LocalDate firstDayOfVacation, LocalDate lastDayOfVacation);
}
