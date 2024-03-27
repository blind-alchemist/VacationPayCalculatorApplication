package ru.trofimova.vacationPayCalculator.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trofimova.vacationPayCalculator.model.Calculator;
import ru.trofimova.vacationPayCalculator.service.CalculatorService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {


    private static final double DAYS_ON_AVERAGE_IN_MONTH = 29.3;
    private final Calculator calculator;

    @Override
    public String vacationPay(double averageSalary, LocalDate firstDayOfVacation, LocalDate lastDayOfVacation) {
            if (!dayOfVacationCheck(firstDayOfVacation, lastDayOfVacation)) {
                return "Проверьте правильность выбора даты";
            } else if (!averageSalaryCheck(averageSalary)) {
                return "Средняя заработная плата не может быть меньше нуля";
            }
            int totalVacationDays = totalVacationDays(firstDayOfVacation, lastDayOfVacation);
            double vacationPay = averageSalary * (totalVacationDays / DAYS_ON_AVERAGE_IN_MONTH);
            double formatVacationPay = Math.round(100.0 * vacationPay) / 100.0;
            return "Сумма отпускных: " + String.valueOf(formatVacationPay);
    }

    private int totalVacationDays (LocalDate firstDayOfVacation, LocalDate lastDayOfVacation) {
        int totalVacationDays = 0;
        for (LocalDate i = firstDayOfVacation;
             !i.isAfter(lastDayOfVacation);
             i = i.plusDays(1)) {
            if (!isHoliday(i)) {
                totalVacationDays++;
            }
        }
        return totalVacationDays;
    }


    private boolean dayOfVacationCheck (LocalDate firstDayOfVacation, LocalDate lastDayOfVacation) {
        return  firstDayOfVacation != null &&
                lastDayOfVacation != null &&
                firstDayOfVacation.isBefore(lastDayOfVacation)  &&
                !firstDayOfVacation.isEqual(lastDayOfVacation);
    }

    private boolean averageSalaryCheck (double averageSalary) {
        return averageSalary > 0;
    }

    private boolean isHoliday(LocalDate localDate) {
        return isWeekend(localDate) || PublicHolidays.publicHolidays.contains(toDate(localDate));
    }

    private static MonthDay toDate(LocalDate localDate) {
        return MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
    }

    private boolean isWeekend(LocalDate localDate) {
        final DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }


    private static class PublicHolidays {
        public final static Set<MonthDay> publicHolidays = new HashSet<>(Arrays.asList(
                MonthDay.of(Month.JANUARY, 1),
                MonthDay.of(Month.JANUARY, 2),
                MonthDay.of(Month.JANUARY, 3),
                MonthDay.of(Month.JANUARY, 4),
                MonthDay.of(Month.JANUARY, 5),
                MonthDay.of(Month.JANUARY, 6),
                MonthDay.of(Month.JANUARY, 7),
                MonthDay.of(Month.JANUARY, 8),
                MonthDay.of(Month.FEBRUARY, 23),
                MonthDay.of(Month.MARCH, 8),
                MonthDay.of(Month.MAY, 1),
                MonthDay.of(Month.MAY, 9),
                MonthDay.of(Month.JUNE, 12),
                MonthDay.of(Month.NOVEMBER, 4),
                MonthDay.of(Month.DECEMBER, 30),
                MonthDay.of(Month.DECEMBER, 31)
        ));
    }

}
