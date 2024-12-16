Feature: Client main menu
  To let clients to choose what to do or where to go when they are signed in their accounts, from viewing programs to viewing personal info, etc.

  Scenario: Client chose to sign out (number version)
    When client enters "1"
    Then send client to "sign out" menu
    Then clear the current signed in client

  Scenario: Client chose to sign out info (text version)
    When client enters "sign out"
    Then send client to "sign out" menu
    Then clear the current signed in client

  Scenario: Client chose to view personal info (number version)
    When client enters "3"
    Then send client to "view personal info" menu

  Scenario: Client chose to view personal info (text version)
    When client enters "view personal info"
    Then send client to "view personal info" menu

  Scenario: Client chose to change personal info (number version)
    When client enters "4"
    Then send client to "change personal info" menu

  Scenario: Client chose to change personal info (text version)
    When client enters "change personal info"
    Then send client to "change personal info" menu

  Scenario: Client chose to view programs (number version)
    When client enters "5"
    Then send client to "view programs" menu

  Scenario: Client chose to view programs (text version)
    When client enters "view programs"
    Then send client to "view programs" menu

  Scenario: Client chose to review completed programs (number version)
    When client enters "6"
    Then send client to "write a review" menu

  Scenario: Client chose to review completed programs (text version)
    When client enters "write a review"
    Then send client to "write a review" menu

  Scenario: Client chose to join a programs (number version)
    When client enters "7"
    Then send client to "join a program" menu

  Scenario: Client chose to join a programs (text version)
    When client enters "join a program"
    Then send client to "join a program" menu

  Scenario: Client chose a wrong input (number version)
    When client enters "9"
    Then send client to "main menu" menu

  Scenario: Client chose a wrong input (text version)
    When client enters "delete self"
    Then send client to "main menu" menu