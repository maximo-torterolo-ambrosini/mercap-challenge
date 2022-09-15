package app;

import app.bill.system.console.BillUIHandler;
import app.bill.system.console.CallUIHandler;
import app.bill.system.console.validator.CallInputValidator;
import app.bill.system.model.Bill;
import app.bill.system.model.BillHandler;
import app.bill.system.model.Call;
import app.bill.system.model.NationalCall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void run() {
        System.out.println("Welcome to the Phone Calls Bill System!");
        NationalCall residedIn = clientLocation();

        System.out.println("First, we need to register at least a call.");
        List<Call> calls = new ArrayList<>();
        calls.add(CallUIHandler.createCall());
        CallUIHandler.printCall(calls.get(0));
        int option;
        do {
            option = showMenu();
            switch (option) {
                case 1 -> {
                    Call c = CallUIHandler.createCall();
                    calls.add(c);
                    System.out.println("Call added successfully!");
                    CallUIHandler.printCall(c);
                }

                case 2 -> {
                    Bill b = BillHandler.createBill(residedIn, calls);
                    System.out.println("Bill created successfully!");
                    BillUIHandler.printBill(b);
                    System.out.println("Thank you for using the Phone Calls Bill System!");
                    System.exit(0);
                }
                case 3 -> {
                    System.out.println("Aborting...");
                    System.exit(0);
                }
            }
        } while (option != 3);

    }

    public static int showMenu() {
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("What do you want to do?");
            System.out.println("1 - Register a new call");
            System.out.println("2 - Show the bill");
            System.out.println("3 - Exit");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid option");
                return showMenu();
            }
        } while (option < 1 || option > 3);
        return option;
    }

    public static NationalCall clientLocation() {
        System.out.println("Where are you from?");
        for (NationalCall nationalCall : NationalCall.values()) {
            System.out.println(nationalCall.getCode() + " - " + nationalCall.getName());
        }
        Scanner scanner = new Scanner(System.in);
        int calledFromCode;
        try {
            calledFromCode = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input");
            return clientLocation();
        }
        // This could be done with an exception and handle it in the previous catch block
        if (CallInputValidator.validateCalledFrom(calledFromCode)) {
            System.out.println("Invalid input");
            return clientLocation();
        }
        return NationalCall.getCallPriceEnumByCode(calledFromCode);
    }
}
