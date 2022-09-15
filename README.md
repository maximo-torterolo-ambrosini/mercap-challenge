# Mercap Challenge

*This is a small challenge that I made for a technical challenge at Mercap.*

The system is only responsible for creating a Monthly bill of phone calls.

Requirements:
- The billing MUST be done monthly
- The bill is composed by:
  - Basic monthly fee
  - Consumption of Local calls.
  - Consumption of National and International Calls.
- Local calls have different prices depending on if it's a high-traffic period (between 8 AM to 8 PM) or if it's a weekend.
- National and International calls have different prices depending on where are you calling.

Considerations I had:
- If Local calls have different prices depending on the traffic or the weekday National and International calls too.
- One bill contains many or zero calls but a call has a bill.
- Since I didn't need to use a Persistence layer I created enums for National and International Prices (Only for this example)
- The billing can only be done monthly so when you try to create a bill with calls of a different month of the emission date an exception will be thrown.
