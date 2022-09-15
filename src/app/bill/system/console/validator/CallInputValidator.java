package app.bill.system.console.validator;

import app.bill.system.model.ICallPriceEnum;
import app.bill.system.model.NationalCall;

import java.time.format.DateTimeFormatter;

public class CallInputValidator {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static boolean validateCalledFrom(int calledFromCode) {
        return calledFromCode < 1 || calledFromCode > NationalCall.values().length;
    }

    public static boolean validateCallType(int callTypeCode) {
        return callTypeCode < 1 || callTypeCode > 2;
    }

    public static boolean validateCalledTo(int calledToCode, ICallPriceEnum[] callPriceEnum) {

        return calledToCode < 1 || calledToCode > callPriceEnum.length;
    }
}
