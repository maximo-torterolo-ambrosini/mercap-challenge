package app.bill.system.console;

import app.bill.system.model.Bill;

import java.time.format.DateTimeFormatter;

public class BillUIHandler {
    public static void printBill(Bill b) {
        System.out.println("==================================");
        System.out.println("Bill:");
        System.out.println("ID: " + b.getId());
        System.out.println("Emission date: " + b.getEmissionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println("Resided in: " + b.getResidedIn().getName());
        System.out.println("Basic fee: " + b.getBasicFeePrice());
        System.out.println("Total: " + b.getTotalPrice());
        System.out.println("==================================");
        System.out.println("Calls:");

        System.out.println("Local calls:");
        b.getLocalCalls().forEach(CallUIHandler::printCall);
        System.out.println("- - - - - - - - - - - - - - - - - ");
        System.out.println("National calls:");
        b.getNationalCalls().forEach(CallUIHandler::printCall);
        System.out.println("- - - - - - - - - - - - - - - - - ");
        System.out.println("International calls:");
        b.getInternationalCalls().forEach(CallUIHandler::printCall);
        System.out.println("==================================");
    }
}
