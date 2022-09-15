package app.bill.system.console;

import app.bill.system.console.validator.CallInputValidator;
import app.bill.system.model.Call;
import app.bill.system.model.ICallPriceEnum;
import app.bill.system.model.InternationalCall;
import app.bill.system.model.NationalCall;

import java.time.LocalDateTime;
import java.util.Scanner;

// CallInputHandler acts as a bridge between the user and the model
public class CallInputHandler {
    static Scanner scanner;

    public static Call createCall() {
        scanner = new Scanner(System.in);
        NationalCall callFrom = readCallFrom();
        ICallPriceEnum callTo = readCallTo();
        int duration = readDuration();
        LocalDateTime date = readDate();
        return new Call(callFrom, callTo, duration, date);
    }

    private static LocalDateTime readDate() {
        System.out.println("Enter the date of the call (dd/mm/yyyy hh:mm): ");
        String date = scanner.nextLine();
        LocalDateTime parsedDate;
        try {
            parsedDate = LocalDateTime.parse(date, CallInputValidator.DATE_TIME_FORMATTER);
            /*
             * with this block we avoid having to catch the exception to the method
             * verifyCallsMonth, which is called in Bill constructor
             * check package app.bill.system.model.Bill for more information
             */
            if (parsedDate.getMonth() != LocalDateTime.now().getMonth()) {
                System.out.println("The date must be in the current month");
                return readDate();
            }
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return readDate();
        }
        return parsedDate;
    }

    private static int readDuration() {
        System.out.println("Enter the duration of the call in minutes: ");
        int duration;
        try {
            duration = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid duration");
            return readDuration();
        }
        return duration;
    }

    private static NationalCall readCallFrom() {
        System.out.println("Where did you call from?");
        for (NationalCall nationalCall : NationalCall.values()) {
            System.out.println(nationalCall.getCode() + " - " + nationalCall.getName());
        }
        int calledFromCode;
        try {
            calledFromCode = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input");
            return readCallFrom();
        }
        // verify if call from is valid
        if (CallInputValidator.validateCalledFrom(calledFromCode)) {
            System.out.println("Invalid Code");
            return readCallFrom();
        }

        return NationalCall.getCallPriceEnumByCode(calledFromCode);
    }

    private static ICallPriceEnum readCallTo() {
        System.out.println("Your call is:");
        System.out.println("1 - National");
        System.out.println("2 - International");
        int callTypeCode;
        try {
            callTypeCode = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input");
            return readCallTo();
        }
        // verify if call type is valid
        if (CallInputValidator.validateCallType(callTypeCode)) {
            System.out.println("Invalid Code");
            return readCallTo();
        }

        ICallPriceEnum[] callToType = callTypeCode == 1 ? NationalCall.values() : InternationalCall.values();

        System.out.println("Where did you call to?");
        for (ICallPriceEnum callTo : callToType) {
            System.out.println(callTo.getCode() + " - " + callTo.getName());
        }
        int calledToCode;
        try {
            calledToCode = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input");
            return readCallTo();
        }
        // verify if call to is valid
        if (CallInputValidator.validateCalledTo(calledToCode, callToType)) {
            System.out.println("Invalid Code");
            return readCallTo();
        }

        return callTypeCode == 1 ? NationalCall.getCallPriceEnumByCode(calledToCode) :
                InternationalCall.getCallPriceEnumByCode(calledToCode);

    }
}


