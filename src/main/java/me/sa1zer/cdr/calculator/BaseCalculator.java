package me.sa1zer.cdr.calculator;

import me.sa1zer.cdr.dto.CdrDto;
import me.sa1zer.cdr.dto.ReportDto;

import java.util.List;

public interface BaseCalculator {

    List<ReportDto> calculate(List<CdrDto> cdrList);
}
