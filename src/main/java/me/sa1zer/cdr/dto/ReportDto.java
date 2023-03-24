package me.sa1zer.cdr.dto;

import me.sa1zer.cdr.dto.enums.CallType;
import me.sa1zer.cdr.dto.enums.TariffType;

import java.time.LocalDateTime;

public class ReportDto {

    private final String phone;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final long duration;
    private final double cost;
    private final CallType callType;
    private final TariffType tariffType;

    public ReportDto(String phone, LocalDateTime startTime, LocalDateTime endTime, long duration, double cost, CallType callType, TariffType tariffType) {
        this.phone = phone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.cost = cost;
        this.callType = callType;
        this.tariffType = tariffType;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public long getDuration() {
        return duration;
    }

    public String getDurationString() {
        long diffSeconds = duration % 60;
        long diffMinutes = duration / 60 % 60;
        long diffHours = duration / (60 * 60) % 24;

        return String.format("%s:%s:%s",
                diffHours < 10 ? "0" + diffHours : diffHours,
                diffMinutes < 10 ? "0" + diffMinutes : diffMinutes,
                diffSeconds < 10 ? "0" + diffSeconds : diffSeconds);
    }

    public double getCost() {
        return cost;
    }

    public CallType getCallType() {
        return callType;
    }

    public TariffType getTariffType() {
        return tariffType;
    }
}
