Feature: Updating account data for clients
  To change a client's password
  Scenario: Admin tries to change the password for a non-existing client username
    When admin enters username "not_a_client" and password "123456789"
    Then tell the admin that this username does not exist

  Scenario: Admin tries to change the password to a one that is too short
    When admin enters username "adam" and password "Gojo"
    Then tell the admin that this password is too short

  Scenario: Admin tries to change the password to a good one
    When admin enters username "adam" and password "Son_Goku_real"
    Then tell the admin that the update to the password was successful