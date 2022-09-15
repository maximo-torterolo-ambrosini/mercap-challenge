package app.bill.system.model;

import java.time.LocalDateTime;


public final class Call {


    private NationalCall calledFrom;
    private ICallPriceEnum calledTo;
    private int duration;
    private LocalDateTime date;

    public Call(NationalCall calledFrom, ICallPriceEnum calledTo, int duration, LocalDateTime date) {
        this.calledFrom = calledFrom;
        this.calledTo = calledTo;
        this.duration = duration;
        this.date = date;
    }

    public String getTypeOfCall() {
        if (calledTo instanceof NationalCall) {
            if (calledFrom.equals(calledTo)) {
                return "LOCAL";
            }
            return "NATIONAL";
        }
        return "INTERNATIONAL";
    }

    public float getCost() {
        float rushHourPrice;
        float offPeakPrice;
        float weekendPrice;
        if (calledFrom.equals(calledTo)) {
            // Business Rule: if the call is made in the same state the price is different
            float SAME_STATE_RUSH_HOUR_PRICE = 0.20f;
            float SAME_STATE_OFF_PEAK_PRICE = 0.10f;
            float SAME_STATE_WEEKEND_PRICE = 0.10f;

            rushHourPrice = SAME_STATE_RUSH_HOUR_PRICE;
            offPeakPrice = SAME_STATE_OFF_PEAK_PRICE;
            weekendPrice = SAME_STATE_WEEKEND_PRICE;

        } else {
            // Business Rule: if the call is made to another state/country the price is different
            rushHourPrice = calledTo.getRushHourPrice();
            offPeakPrice = calledTo.getOffPeakPrice();
            weekendPrice = calledTo.getWeekendPrice();
        }
        return calculateCost(rushHourPrice, offPeakPrice, weekendPrice);
    }

    // This method is related with the business logic of the application
    private float calculateCost(float rushHourPrice, float offPeakPrice, float weekendPrice) {
        // Business Rule: if the call is made on a weekend the price is different
        if (date.getDayOfWeek().getValue() >= 6) {
            return duration * weekendPrice;
        }
        /* Business Rule:
         * if the call is made between 8:00AM and 8:00PM on a business day the price is different
         */
        int RUSH_HOUR_START = 8;
        int RUSH_HOUR_END = 20;
        if (date.getHour() >= RUSH_HOUR_START && date.getHour() <= RUSH_HOUR_END) {
            return duration * rushHourPrice;
        }
        return duration * offPeakPrice;
    }


    // Getters and setters

    public NationalCall getCalledFrom() {
        return calledFrom;
    }

    public void setCalledFrom(NationalCall calledFrom) {
        this.calledFrom = calledFrom;
    }

    public ICallPriceEnum getCalledTo() {
        return calledTo;
    }

    public void setCalledTo(ICallPriceEnum calledTo) {
        this.calledTo = calledTo;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    // toString method
    @Override
    public String toString() {
        return "Call{" +
                "calledFrom=" + calledFrom +
                ", calledTo=" + calledTo +
                ", duration=" + duration +
                ", date=" + date +
                '}';
    }
}
