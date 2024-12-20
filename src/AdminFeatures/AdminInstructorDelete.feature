Feature: Deleting account data for instructors
  Self-explanatory
  Scenario: Admin tries to delete a non-existing instructor
    When admin enters instructor username "not_an_instructor"
    Then tell the admin that this instructor username does not exist

  Scenario: Admin tries to delete an existing client instructor
    When admin enters instructor username "sameer_not_official"
    Then tell the admin that the deletion was  successful