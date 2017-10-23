Part 1: Manual Testing
We are assuming here that this form is what the business requested. We are not considering here obvious design overlooks like the fact
that there are no hints, no fixed prices, no different type of rooms, no booking limit, no information about the hotel, and the data
the user needs to input is missing at the very least the amount of people that are going to stay in the room, and how many are underage.
There is also no cookie policy message, and no privacy policy, which is against the european law.

1. Open the page with the browser inspect elements menu open: There are several errors in the console:
Uncaught TypeError: Cannot read property 'bookingid' of undefined
    at getBooking (script.js:15)
    at Object.success (script.js:26)
    at i (jquery-2.2.3.min.js:2)
    at Object.fireWith [as resolveWith] (jquery-2.2.3.min.js:2)
    at z (jquery-2.2.3.min.js:4)
    at XMLHttpRequest.<anonymous> (jquery-2.2.3.min.js:4)
2. Use https://validator.w3.org/nu/ to validate the HTML format of the page, this passed OK.
3. Click save with all the fields empty: This returns an unhandled 500 status code. This means that not only the front end is not handling
this properly, but also the backend is not failing gracefully.
4. Write "aaa" in all the fields that allow it: I get another 500 status code, apparently due to price being letters.
5. Attempt to book in the past, with a checkout date before the check in date: It was allowed. All dates should be in the present/future
and check out date should always be after check in date (there is the caveat that in some places check in can be 1 day in the past, if
for example, someone in Japan was booking a hotel in California).
6. Attempt a "happy path booking". It was allowed.
7. Attempt to create a booking deleting the check out date after selecting it, leaving "201". The booking was allowed and
the check out date is "1970-01-01"
8. Attempt a booking writing "1" in every field (and deposit true). Was able to do the booking, both dates are "1970-01-01"
9. Attempt to delete a booking. Allowed. Works fine.
10. Attempt an html/JavaScript injection: I actually did this on Friday, fields are not being validate properly.
11. Attempt to write a large number in the price field, was allowed and looks terrible.
12. Attempt to create two identical bookings. It is allowed, this should be alerted.

Part 2: Selenium test:
Used Cucumber/Selenium WebDriver. Tested the happy path of the two distinguished features: Make a booking, and delete a booking.
This only will support Chrome since it is a code test, we are going to assume most of the traffic comes from this browser.
