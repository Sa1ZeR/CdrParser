package me.sa1zer.cdr.calculator;

import me.sa1zer.cdr.dto.CdrDto;
import me.sa1zer.cdr.dto.ReportDto;
import me.sa1zer.cdr.dto.enums.CallType;
import me.sa1zer.cdr.dto.enums.TariffType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnlimitedTariffCalculatorTest {

    @Test
    void calculate() {
        BaseCalculator calculator = new UnlimitedTariffCalculator();

        List<CdrDto> cdrDtoList = new ArrayList<>();
        //110
        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 10, 18, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 10, 18, 53, 1),
                CallType.OUTGOING, TariffType.UNLIMITED));
        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 10, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 10, 14, 53, 1),
                CallType.OUTGOING, TariffType.UNLIMITED));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 5, 11, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 5, 13, 53, 1),
                CallType.OUTGOING, TariffType.UNLIMITED));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 7, 11, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 7, 15, 53, 1),
                CallType.OUTGOING, TariffType.UNLIMITED));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 11, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 11, 13, 43, 1),
                CallType.OUTGOING, TariffType.UNLIMITED));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 13, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 13, 13, 43, 1),
                CallType.INCOMING, TariffType.UNLIMITED));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 15, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 15, 13, 43, 1),
                CallType.INCOMING, TariffType.UNLIMITED));

        List<ReportDto> calculated = calculator.calculate(cdrDtoList);

        double sum = calculated.stream().mapToDouble(ReportDto::getCost).sum();

        Assertions.assertEquals(230D, sum); //230 because 300 min = 100 rub, but here they are not included
    }

    @Test
    void calculate1() {
        BaseCalculator calculator = new UnlimitedTariffCalculator();

        List<CdrDto> cdrDtoList = new ArrayList<>();
        //110
        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 10, 18, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 10, 18, 53, 1),
                CallType.OUTGOING, TariffType.UNLIMITED));
        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 10, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 10, 14, 53, 1),
                CallType.INCOMING, TariffType.UNLIMITED));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 11, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 11, 13, 43, 1),
                CallType.OUTGOING, TariffType.UNLIMITED));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 13, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 13, 13, 43, 1),
                CallType.INCOMING, TariffType.UNLIMITED));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 15, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 15, 13, 43, 1),
                CallType.INCOMING, TariffType.UNLIMITED));

        List<ReportDto> calculated = calculator.calculate(cdrDtoList);

        double sum = calculated.stream().mapToDouble(ReportDto::getCost).sum();

        Assertions.assertEquals(0D, sum); //0 because 300 min = 100 rub, but here they are not included
    }
}