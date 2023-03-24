package me.sa1zer.cdr.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;

class TimeUtilsTest {

    @Test
    void parseTimeFromString() throws ParseException {
        String time = "20230330212146";
        LocalDateTime localDateTime = TimeUtils.parseTimeFromString(time);

        Assertions.assertEquals("2023-03-30T21:21:46", localDateTime.toString());
    }
}