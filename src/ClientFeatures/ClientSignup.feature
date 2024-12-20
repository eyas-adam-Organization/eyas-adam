Feature: Creation of accounts for clients
  To let Clients have the ability to create their own accounts
  Scenario: Client used an already used username
    When client entered username "adam" and password "555555555" for program "Weight Loss"
    Then tell client that the username is used
    Then redirect client to sign up

  Scenario: Client entered a short password
    When client entered username "user123" and password "555" for program "Weight Loss"
    Then tell the used that the password is short
    Then redirect client to sign up

  Scenario: Client entered a non-registered program
    When client entered username "user123" and password "555555555" for program "not program"
    Then tell client that the program is not a registered program
    Then redirect client to sign up

  Scenario: Client entered a new username, a good password and a registered program
    When client entered username "user123" and password "555555555" for program "Weight Loss Basic"
    Then tell the user that the account was created successfully
    Then sign the user in with username "user123"
    Then add the user "user123" to the list of clients registered in the program "Weight Loss Basic"
