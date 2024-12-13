Feature: Creation of accounts for clients
  To let Clients have the ability to create their own accounts
  Scenario: Client used an already used username
    When client entered username "adam" and password "555555555"
    Then tell client that the username is used

  Scenario: Client entered a short password
    When client entered username "user123" and password "555"
    Then tell the used that the password is short

  Scenario: Client used a new username and a good password
    When client entered username "user123" and password "555555555"
    Then tell the user that the account was created successfully for user "user123"
    And sign the user in with username "user123"
