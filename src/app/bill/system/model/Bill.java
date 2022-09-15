package app.bill.system.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

public final class Bill {
    private final float basicFeePrice;
    private final UUID id;
    NationalCall residedIn;
    private final List<Call> localCalls;
    private final List<Call> nationalCalls;
    private final List<Call> internationalCalls;

    private final LocalDateTime emissionDate;

    public Bill(float basicFeePrice, NationalCall residedIn, List<Call> localCalls, List<Call> nationalCalls, List<Call> internationalCalls) {
        this.basicFeePrice = basicFeePrice;
        this.id = UUID.randomUUID();
        this.emissionDate = LocalDateTime.now();
        this.residedIn = residedIn;
        verifyCallsMonth(localCalls, emissionDate.getMonth());
        this.localCalls = localCalls;
        this.nationalCalls = nationalCalls;
        verifyCallsMonth(nationalCalls, emissionDate.getMonth());
        this.internationalCalls = internationalCalls;
        verifyCallsMonth(internationalCalls, emissionDate.getMonth());
    }

    /*
     * This method is related to the business logic of the application.
     * Since the billing process is made monthly, the calls of a month MUST be made in the same month
     */

    private static void verifyCallsMonth(List<Call> c, Month month) {
        c.stream().filter(call -> call.getDate().getMonth() != month).findFirst().ifPresent(call -> {
            if (call.getDate().getMonth() != month) {
                throw new IllegalArgumentException("The calls must be from the same month as the bill");
            }
        });
    }

    public float calculateCallsPrice(List<Call> calls) {
        return (float) calls.stream().mapToDouble(Call::getCost).sum();
    }

    public float getTotalPrice() {
        return basicFeePrice +
                calculateCallsPrice(localCalls) +
                calculateCallsPrice(nationalCalls) +
                calculateCallsPrice(internationalCalls);
    }
    // Getters

    public float getBasicFeePrice() {
        return basicFeePrice;
    }

    public UUID getId() {
        return id;
    }

    public NationalCall getResidedIn() {
        return residedIn;
    }

    public List<Call> getLocalCalls() {
        return localCalls;
    }


    public List<Call> getNationalCalls() {
        return nationalCalls;
    }

    public List<Call> getInternationalCalls() {
        return internationalCalls;
    }

    public LocalDateTime getEmissionDate() {
        return emissionDate;
    }

    // toSring() method
    @Override
    public String toString() {
        return "Bill{" +
                "basicFeePrice=" + basicFeePrice +
                ", id=" + id +
                ", residedIn=" + residedIn +
                ", localCalls=" + localCalls +
                ", nationalCalls=" + nationalCalls +
                ", internationalCalls=" + internationalCalls +
                ", emissionDate=" + emissionDate +
                '}';
    }
}
