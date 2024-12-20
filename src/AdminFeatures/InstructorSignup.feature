Feature: Instructor Signup

  Scenario: Instructor used an already used username
    When instructor entered username "waleed123" and password "123456789"
    Then tell instructor that the username is used

  Scenario: Client entered a short password
    When instructor entered username "user123" and password "555"
    Then tell the instructor that the password is short

  Scenario: Client entered a new username, a good password and a registered program
    When instructor entered username "user123" and password "555555555"
    Then tell the instructor that the account was created successfully
    Then add the instructor "user123" to the list of instructors