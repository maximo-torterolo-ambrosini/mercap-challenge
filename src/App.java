import app.Menu;
import app.bill.system.console.BillUIHandler;
import app.bill.system.console.validator.CallInputValidator;
import app.bill.system.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Made with ♥ by Maximo Torterolo Ambrosini

public class App {
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("test")) {
                test();
            }
        } else {
            Menu.run();
        }


    }

    public static void test() {
        List<Call> calls = new ArrayList<>();
        calls.add(new Call(NationalCall.TF, NationalCall.TF, 10,
                LocalDateTime.parse("15/09/2022 14:26", CallInputValidator.DATE_TIME_FORMATTER)));
        calls.add(new Call(NationalCall.TF, NationalCall.TF, 23,
                LocalDateTime.parse("17/09/2022 12:16", CallInputValidator.DATE_TIME_FORMATTER)));

        calls.add(new Call(NationalCall.TF, NationalCall.BA, 40,
                LocalDateTime.parse("19/09/2022 17:26", CallInputValidator.DATE_TIME_FORMATTER)));
        calls.add(new Call(NationalCall.TF, NationalCall.LP, 13,
                LocalDateTime.parse("12/09/2022 21:01", CallInputValidator.DATE_TIME_FORMATTER)));

        calls.add(new Call(NationalCall.TF, InternationalCall.AU, 20,
                LocalDateTime.parse("15/09/2022 13:26", CallInputValidator.DATE_TIME_FORMATTER)));
        calls.add(new Call(NationalCall.TF, InternationalCall.US, 30,
                LocalDateTime.parse("11/09/2022 14:26", CallInputValidator.DATE_TIME_FORMATTER)));

        Bill b = BillHandler.createBill(NationalCall.TF, calls);
        BillUIHandler.printBill(b);
    }

}