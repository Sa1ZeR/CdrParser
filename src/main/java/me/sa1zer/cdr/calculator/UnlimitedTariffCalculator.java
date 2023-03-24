package me.sa1zer.cdr.calculator;

import me.sa1zer.cdr.dto.CdrDto;
import me.sa1zer.cdr.dto.ReportDto;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UnlimitedTariffCalculator implements BaseCalculator {

    private static final double AFTER_LIMIT_PRICE = 1.0D; //after 300 min
    private static final double BEFORE_LIMIT_PRICE = 0.0D; //after 300 min
    @Override
    public List<ReportDto> calculate(List<CdrDto> cdrList) {
        List<ReportDto> reports = new ArrayList<>();

        long freeMinutes = 300;
        boolean isExpired = false;

        for(CdrDto cdrDto : cdrList) {
            long duration = cdrDto.getStart().until(cdrDto.getEnd(), ChronoUnit.SECONDS);
            long paidMinutes = 0;

            double durationInMinutes = Math.ceil(duration) / 60D;

            if(freeMinutes - durationInMinutes < 0) {
                isExpired = true;

                paidMinutes = (long) Math.abs(freeMinutes - durationInMinutes);
            }
            freeMinutes-=durationInMinutes;

            double price = isExpired ? paidMinutes * AFTER_LIMIT_PRICE
                    : BEFORE_LIMIT_PRICE;

            if(freeMinutes < 0)
                freeMinutes = 0;

            reports.add(new ReportDto(cdrDto.getNumber(), cdrDto.getStart(), cdrDto.getEnd(),
                    duration, price, cdrDto.getCallType(), cdrDto.getTariffType()));
        }
        return reports;
    }
}
