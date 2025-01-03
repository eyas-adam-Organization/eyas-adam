Feature: View Statistics On A program
  Scenario: view the attendance and completion rates of the clients enrolled i a program1
    Given i have the following Statistics on the program "program1"
      |clientID | completion |  attendance  |
      | 1       | 20         |  30          |
      | 2       | 50         |  40          |
      | 3       | 10         |  60          |
      | 4       | 90         |  10          |

    When i view the Statistics of this program
    Then the system should view the data
     And the message "the of program1 is viewed";

  Scenario: view the attendance and completion rates of the clients enrolled i a program2
    Given i have the following Statistics on the program "program2"
      |clientID | completion |  attendance  |
      | 6       | 40         |  36          |
      | 7       | 50         |  39          |

    When i view the Statistics of this program
    Then the system should view the data
    And the message "there is an error in the details of client 5";

   Scenario: trying to view Statistics of an non-existing program
     Given the missing program is "programNOT"
     When i view the Statistics of this program
     Then the system should not view any data
     And the message "this program does not exist";

  Scenario: trying to view Statistics of a program with no clients
    Given the missing program is "program5"
    When i view the Statistics of this program
    Then the system should not view any data
    And the message "this program does not have any clients";


