package me.sa1zer.cdr.calculator;

import me.sa1zer.cdr.dto.CdrDto;
import me.sa1zer.cdr.dto.ReportDto;
import me.sa1zer.cdr.dto.enums.CallType;
import me.sa1zer.cdr.dto.enums.TariffType;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

class DefaultTariffCalculatorTest {

    @Test
    public void calculate() {
        BaseCalculator calculator = new DefaultTariffCalculator();

        List<CdrDto> cdrDtoList = new ArrayList<>();
        //110
        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 10, 18, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 10, 18, 53, 1),
                        CallType.OUTGOING, TariffType.DEFAULT));
        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 10, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 10, 14, 53, 1),
                CallType.OUTGOING, TariffType.DEFAULT));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 11, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 11, 13, 43, 1),
                CallType.OUTGOING, TariffType.DEFAULT));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 13, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 13, 13, 43, 1),
                CallType.INCOMING, TariffType.DEFAULT));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 15, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 15, 13, 43, 1),
                CallType.INCOMING, TariffType.DEFAULT));

        List<ReportDto> calculated = calculator.calculate(cdrDtoList);

        double sum = calculated.stream().mapToDouble(ReportDto::getCost).sum();

        Assertions.assertEquals(65D, sum);
    }

    @Test
    public void calculate2() {
        BaseCalculator calculator = new DefaultTariffCalculator();

        List<CdrDto> cdrDtoList = new ArrayList<>();
        //110
        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 10, 18, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 10, 18, 53, 1),
                CallType.OUTGOING, TariffType.DEFAULT));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 13, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 13, 13, 43, 1),
                CallType.INCOMING, TariffType.DEFAULT));

        cdrDtoList.add(new CdrDto("+79033333333",
                LocalDateTime.of(2023, Month.JANUARY, 15, 13, 33, 1),
                LocalDateTime.of(2023, Month.JANUARY, 15, 13, 43, 1),
                CallType.INCOMING, TariffType.DEFAULT));

        List<ReportDto> calculated = calculator.calculate(cdrDtoList);

        double sum = calculated.stream().mapToDouble(ReportDto::getCost).sum();

        Assertions.assertEquals(10D, sum);
    }

}