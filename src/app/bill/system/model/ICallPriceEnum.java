package app.bill.system.model;

public interface ICallPriceEnum {

    int getCode();

    float getRushHourPrice();

    float getOffPeakPrice();

    float getWeekendPrice();

    String getName();

}
