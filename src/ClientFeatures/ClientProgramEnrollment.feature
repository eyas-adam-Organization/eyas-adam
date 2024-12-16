Feature: Program Enrollment for Clients
  Self-explanatory
  Scenario: Client Tried to enroll in a non-existing program
    When A registered client "eyas" tries to enroll in a program "Not_program"
    Then tell client that this program is not a registered program
    Then send  client to "join a program"

  Scenario: Client Tried to enroll in a registered program they are already in
    When A registered client "eyas" tries to enroll in a program "Weight Loss Basic"
    Then tell client that they are already in the program
    Then send  client to "join a program"

  Scenario: Client Tried to enroll in a registered program that they are not in
    When A registered client "eyas" tries to enroll in a program "Muscle Mastery"
    Then tell client that they were successfully enrolled in the program
    Then send  client to "main menu"