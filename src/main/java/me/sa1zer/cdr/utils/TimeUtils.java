package me.sa1zer.cdr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class TimeUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private TimeUtils() {

    }

    public static LocalDateTime parseTimeFromString(String s) throws ParseException {
        return sdf.parse(s).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static String LocalDateTimeToString(LocalDateTime time) {
        return sdfs.format(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()));
    }
}
