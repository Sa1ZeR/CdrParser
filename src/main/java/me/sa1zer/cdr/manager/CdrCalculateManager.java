package me.sa1zer.cdr.manager;

import me.sa1zer.cdr.calculator.PerMinutedTariffCalculator;
import me.sa1zer.cdr.calculator.BaseCalculator;
import me.sa1zer.cdr.calculator.DefaultTariffCalculator;
import me.sa1zer.cdr.calculator.UnlimitedTariffCalculator;
import me.sa1zer.cdr.dto.CdrDto;
import me.sa1zer.cdr.dto.ReportDto;
import me.sa1zer.cdr.dto.enums.TariffType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdrCalculateManager {

    private static final HashMap<TariffType, BaseCalculator> CALCULATORS = new HashMap<>();

    static {
        CALCULATORS.put(TariffType.DEFAULT, new DefaultTariffCalculator());
        CALCULATORS.put(TariffType.PER_MINUTE, new PerMinutedTariffCalculator());
        CALCULATORS.put(TariffType.UNLIMITED, new UnlimitedTariffCalculator());
    }

    private final CdrParseManager cdrParseManager;
    private final ReportManager reportManager;

    public CdrCalculateManager(CdrParseManager cdrParseManager, ReportManager reportManager) {
        this.cdrParseManager = cdrParseManager;
        this.reportManager = reportManager;
    }

    public void calculate() {
        Map<String, List<CdrDto>> execute = cdrParseManager.executeParse();

        execute.forEach((key, value) -> {
            if(value.size() > 0) {
                CdrDto cdrDto = value.get(0);

                List<ReportDto> calculated = CALCULATORS.get(cdrDto.getTariffType()).calculate(value);
                reportManager.save(key, calculated);
            }
        });

        System.out.println("Successfully calculated!");
    }
}
