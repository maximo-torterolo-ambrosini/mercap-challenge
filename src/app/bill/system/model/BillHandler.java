package app.bill.system.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BillHandler is a class responsible for creating a bill acting as an Adapter between the UI and the Bill class.
public class BillHandler {
    // Business Rule: The bill has a basic monthly fee
    private static final int BASE_FEE = 230;

    public static Bill createBill(NationalCall residedIn, List<Call> calls) {
        Map<String, List<Call>> callsByType = filterCalls(calls);
        Month emissionDateMonth = LocalDateTime.now().getMonth();
        return new Bill(BASE_FEE, residedIn, callsByType.get("LOCAL"), callsByType.get("NATIONAL"), callsByType.get("INTERNATIONAL"));
    }

    public static Map<String, List<Call>> filterCalls(List<Call> calls) {
        HashMap<String, List<Call>> callsMap = new HashMap<>();
        List<Call> internationalCalls = new ArrayList<>();
        List<Call> localCalls = new ArrayList<>();
        List<Call> nationalCalls = new ArrayList<>();
        callsMap.put("INTERNATIONAL", internationalCalls);
        callsMap.put("LOCAL", localCalls);
        callsMap.put("NATIONAL", nationalCalls);
        calls.forEach(call -> {
            switch (call.getTypeOfCall()) {
                case "INTERNATIONAL" -> internationalCalls.add(call);
                case "NATIONAL" -> nationalCalls.add(call);
                case "LOCAL" -> localCalls.add(call);
            }
        });
        return callsMap;
    }
}
