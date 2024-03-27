package ru.trofimova.vacationPayCalculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.trofimova.vacationPayCalculator.model.Calculator;
import ru.trofimova.vacationPayCalculator.service.Impl.CalculatorServiceImpl;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceImplTest {

    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorServiceImpl(new Calculator());
    }

    @Test
    @DisplayName("Calculation of vacation pay")
    public void testVacationPay() {
        String actual = calculatorService.vacationPay(25000.00,
                LocalDate.of(2023, Month.AUGUST, 10),
                LocalDate.of(2023, Month.AUGUST, 24));
        String expected = "Сумма отпускных: 9385.67";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("End date before start date")
    public void VacationPayIncorrectDateTest () {
        String actual = calculatorService.vacationPay(25000.00,
                LocalDate.of(2023, Month.AUGUST, 29),
                LocalDate.of(2023, Month.AUGUST, 24));
        String expected = "Проверьте правильность выбора даты";
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Average salary is less than 0")
    public void VacationPayIncorrectSalaryTest () {
        String actual = calculatorService.vacationPay(-25000.00,
                LocalDate.of(2023, Month.AUGUST, 10),
                LocalDate.of(2023, Month.AUGUST, 24));
        String expected = "Средняя заработная плата не может быть меньше нуля";
        assertEquals(expected, actual);
    }
}
