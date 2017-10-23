Feature:
  As a user
  I want to be able to use the booking page
  So I can visit the hotel or cancel

  Scenario: Save a booking in the hotel
    Given the user is in the booking page
    When he enters his personal data
    Then the data should be visible

  Scenario: Delete a booking in the hotel
    Given the user has made a booking
    When he clicks the delete button
    Then the booking should be removed