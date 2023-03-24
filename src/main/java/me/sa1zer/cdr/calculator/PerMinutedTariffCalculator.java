package me.sa1zer.cdr.calculator;

import me.sa1zer.cdr.dto.CdrDto;
import me.sa1zer.cdr.dto.ReportDto;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PerMinutedTariffCalculator implements BaseCalculator {

    private static final double PER_MINUTE_PRICE = 1.5;

    @Override
    public List<ReportDto> calculate(List<CdrDto> cdrList) {
        List<ReportDto> reports = new ArrayList<>();

        for(CdrDto cdrDto : cdrList) {
            long duration = cdrDto.getStart().until(cdrDto.getEnd(), ChronoUnit.SECONDS);

            double price = Math.ceil(duration / 60D) * PER_MINUTE_PRICE;

            reports.add(new ReportDto(cdrDto.getNumber(), cdrDto.getStart(), cdrDto.getEnd(),
                    duration, price, cdrDto.getCallType(), cdrDto.getTariffType()));
        }
        return reports;
    }
}
