package app.bill.system.model;

import java.util.Arrays;

public enum NationalCall implements ICallPriceEnum {
    BA(1, 0.20f, 0.10f, 0.10f, "Buenos Aires"),
    LP(2, 0.22f, 0.12f, 0.12f, "La Pampa"),
    TF(3, 0.232f, 0.132f, 0.132f, "Tierra del Fuego");

    private final int code;
    private final float rushHourPrice;
    private final float offPeakPrice;
    private final float weekendPrice;

    private final String name;

    NationalCall(int code, float rushHourPrice, float offPeakPrice, float weekendPrice, String name) {
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

    public static NationalCall getNationalCallByName(String name) {
        return Arrays.stream(NationalCall.values())
                .filter(nationalCall -> nationalCall.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid name: " + name));
    }

    public static NationalCall getCallPriceEnumByCode(int code) {
        return Arrays.stream(NationalCall.values())
                .filter(nationalCall -> nationalCall.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code: " + code));
    }
}
