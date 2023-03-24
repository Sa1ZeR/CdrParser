package me.sa1zer.cdr.calculator;

import me.sa1zer.cdr.dto.CdrDto;
import me.sa1zer.cdr.dto.enums.CallType;
import me.sa1zer.cdr.dto.ReportDto;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DefaultTariffCalculator implements BaseCalculator {

    private static final double INCOMING_PRICE = 0;
    private static final double OUTGOING_PRICE = 0.5D; //first 100 min
    private static final double PER_MINUTE_PRICE = 1.5D; //after 100 min

    @Override
    public List<ReportDto> calculate(List<CdrDto> cdrList) {
        List<ReportDto> reports = new ArrayList<>();

        long freeMinutes = 100; //100 min
        boolean isPerMinute = false;

        for(CdrDto cdrDto : cdrList) {
            long duration = cdrDto.getStart().until(cdrDto.getEnd(), ChronoUnit.SECONDS);
            double durationInMinute = Math.ceil(duration / 60D);

            long usePerMinutePrice = 0;

            double price;

            if(cdrDto.getCallType() == CallType.OUTGOING) {
                if (freeMinutes - durationInMinute < 0) {
                    usePerMinutePrice = (long) (freeMinutes - durationInMinute);

                    if (freeMinutes > 0) {
                        price = (usePerMinutePrice + durationInMinute) * OUTGOING_PRICE +
                                Math.abs(usePerMinutePrice) * PER_MINUTE_PRICE;
                    } else
                        price = Math.abs(usePerMinutePrice) * PER_MINUTE_PRICE;
                } else price = durationInMinute * OUTGOING_PRICE;
            } else price = INCOMING_PRICE;

            freeMinutes-=duration;

            if(freeMinutes < 0)
                freeMinutes = 0;

            reports.add(new ReportDto(cdrDto.getNumber(), cdrDto.getStart(), cdrDto.getEnd(),
                    duration, price, cdrDto.getCallType(), cdrDto.getTariffType()));
        }

        return reports;
    }
}
