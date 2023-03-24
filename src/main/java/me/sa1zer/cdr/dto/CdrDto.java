package me.sa1zer.cdr.dto;

import me.sa1zer.cdr.dto.enums.CallType;
import me.sa1zer.cdr.dto.enums.TariffType;

import java.time.LocalDateTime;

public class CdrDto {

    private final String number;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final CallType callType;
    private final TariffType tariffType;

    public CdrDto(String number, LocalDateTime start, LocalDateTime end, CallType callType, TariffType tariffType) {
        this.number = number;
        this.start = start;
        this.end = end;
        this.callType = callType;
        this.tariffType = tariffType;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public CallType getCallType() {
        return callType;
    }

    public TariffType getTariffType() {
        return tariffType;
    }
}
