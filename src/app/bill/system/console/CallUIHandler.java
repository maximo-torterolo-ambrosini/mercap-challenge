package app.bill.system.console;

import app.bill.system.model.Call;

public class CallUIHandler {

    public static Call createCall() {
        return CallInputHandler.createCall();
    }

    public static void printCall(Call call) {
        System.out.println("[" + call.getTypeOfCall() + "] " + "Call from " + call.getCalledFrom().getName() + " to " + call.getCalledTo().getName() + " on " + call.getDate() + " for " + call.getDuration() + " minutes, cost: $" + call.getCost());
    }
}
