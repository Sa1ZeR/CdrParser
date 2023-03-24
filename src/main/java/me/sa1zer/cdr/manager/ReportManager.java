package me.sa1zer.cdr.manager;

import me.sa1zer.cdr.dto.ReportDto;
import me.sa1zer.cdr.dto.enums.TariffType;
import me.sa1zer.cdr.utils.TimeUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReportManager {

    private static final String PREFIX_FILE = "report_%s.txt";
    private static final String DIR_NAME = "reports";

    public void save(String phone, List<ReportDto> list) {
        prepareDir();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(String.format(DIR_NAME
                + File.separator + PREFIX_FILE, phone)))) {
            ReportDto reportDto = list.get(0);
            bw.write(getHeader(reportDto.getTariffType().getCode(), reportDto.getPhone()));

            List<ReportDto> sorted = list.stream()
                    .sorted(Comparator.comparing(ReportDto::getStartTime))
                    .collect(Collectors.toList());

            for(ReportDto r : sorted) {
                bw.write(getLine(r));
            }

            bw.write(getFooter(list, reportDto.getTariffType() == TariffType.UNLIMITED ? 100 : 0));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareDir() {
        Path dir = Paths.get(DIR_NAME);
        if(!Files.isDirectory(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getHeader(String tariff, String phone) {
        return String.format("Tariff index: %s\n" +
                "----------------------------------------------------------------------------\n" +
                "Report for phone number %s:\n" +
                "----------------------------------------------------------------------------\n" +
                "| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n" +
                "----------------------------------------------------------------------------", tariff,
                phone);
    }

    private String getLine(ReportDto dto) {
        return String.format("\n|     %s    | %s | %s | %s |  %s  |",
                dto.getCallType().getCode(),
                TimeUtils.LocalDateTimeToString(dto.getStartTime()),
                TimeUtils.LocalDateTimeToString(dto.getEndTime()),
                dto.getDurationString(),
                dto.getCost()
        );
    }

    private String getFooter(List<ReportDto> list, double append) {
        double price = append + list.stream().mapToDouble(ReportDto::getCost).sum();

        return String.format("\n----------------------------------------------------------------------------\n" +
                "|                                           Total Cost: |     %.2f rubles |\n" +
                "----------------------------------------------------------------------------", price);
    }
}
