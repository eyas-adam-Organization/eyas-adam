Feature: Deleting account data for clients
  Self-explanatory
  Scenario: Admin tries to delete a non-existing client
    When admin enters username "not_a_client"
    Then tell  the admin that this username does not exist

  Scenario: Admin tries to delete an existing client
    When admin enters username "eyas_not_real"
    Then tell the admin that the deletion was successful