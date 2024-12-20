Feature: Updating account data for instructors
  To change an instructor's password
  Scenario: Admin tries to change the password for a non-existing instructor username
    When admin enters an instructor username "not_a_client" and password "123456789"
    Then tell the  admin that this instructor username does not exist

  Scenario: Admin tries to change the password to a one that is too short
    When admin enters an instructor username "sameer_official" and password "Gojo"
    Then tell the  admin that this password is too short

  Scenario: Admin tries to change the password to a good one
    When admin enters an instructor username "sameer_official" and password "Son_Goku_real"
    Then tell the  admin that the update to the password was successful