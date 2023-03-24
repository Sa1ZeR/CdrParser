package me.sa1zer.cdr.dto.enums;

public enum TariffType {

    DEFAULT("11"), PER_MINUTE("03"), UNLIMITED("06");

    private final String code;

    TariffType(String code) {
        this.code = code;
    }

    public static TariffType getType(String s) {
        for(TariffType t : values()) {
            if(t.code.equals(s))
                return t;
        }
        throw new RuntimeException(String.format("TariffType with code %s not found!", s));
    }

    public String getCode() {
        return code;
    }
}
