package me.sa1zer.cdr.manager;

import me.sa1zer.cdr.dto.CdrDto;
import me.sa1zer.cdr.dto.enums.CallType;
import me.sa1zer.cdr.dto.enums.TariffType;
import me.sa1zer.cdr.utils.TimeUtils;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdrParseManager {

    private static final String CDR_FILE_NAME = "cdr.txt";

    public Map<String, List<CdrDto>> executeParse() {
        Map<String, List<CdrDto>> cdrDB = new HashMap<>();

        try(BufferedReader bis = new BufferedReader(new FileReader(CDR_FILE_NAME))) {
            String s;
            while ((s = bis.readLine()) != null) {
                try {
                    CdrDto cdr = getCdrFromString(s);
                    List<CdrDto> cdrByPhone = cdrDB.getOrDefault(cdr.getNumber(), new ArrayList<>());
                    cdrByPhone.add(cdr);

                    cdrDB.put(cdr.getNumber(), cdrByPhone);
                } catch (ParseException e) {
                    throw new RuntimeException(String.format("Error while parsing cdr line %s", s), e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cdrDB;
    }

    private CdrDto getCdrFromString(String s) throws ParseException {
        String[] split = s.split(",");

        if(split.length != 5)
            throw new RuntimeException(String.format("Incorrect line in file: %s", s));

        String phone = split[1].trim();
        LocalDateTime start = TimeUtils.parseTimeFromString(split[2].trim());
        LocalDateTime end = TimeUtils.parseTimeFromString(split[3].trim());
        CallType callType = CallType.getType(split[0].trim());
        TariffType tariffType = TariffType.getType(split[4].trim());

        return new CdrDto(phone, start, end, callType, tariffType);
    }
}
