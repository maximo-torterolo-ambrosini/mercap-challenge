package app.bill.system.model;

import java.util.Arrays;

public enum InternationalCall implements ICallPriceEnum {
    US(1, 0.30f, 0.20f, 0.21f, "Estados Unidos"),
    UK(2, 0.35f, 0.25f, 0.26f, "Reino Unido"),
    AU(3, 0.40f, 0.30f, 0.31f, "Australia");

    private final int code;
    private final float rushHourPrice;
    private final float offPeakPrice;
    private final float weekendPrice;

    private final String name;

    InternationalCall(int code, float rushHourPrice, float offPeakPrice, float weekendPrice, String name) {
        this.code = code;
        this.rushHourPrice = rushHourPrice;
        this.offPeakPrice = offPeakPrice;
        this.weekendPrice = weekendPrice;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public float getRushHourPrice() {
        return rushHourPrice;
    }

    public float getOffPeakPrice() {
        return offPeakPrice;
    }

    public float getWeekendPrice() {
        return weekendPrice;
    }

    public String getName() {
        return name;
    }

    public static InternationalCall getCallPriceEnumByCode(int code) {
        return Arrays.stream(InternationalCall.values())
                .filter(internationalCall -> internationalCall.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }
}
