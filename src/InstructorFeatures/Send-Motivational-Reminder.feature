Feature: Send-Motivational-Reminder
   # Sending Motivational Reminders
  Scenario Outline: Send motivational reminders to clients with low progress successfully
    Given I have a list of clients with progress data
    And a client's completion rate is below "50"
    When I send motivational reminders to the client
    Then the client "<client>"  shouled reserve the "<reminder>" and the system display "Motivational Reminder has been sent"
    Examples:
      | client | reminder    |
      |  1     |  reserve    |
      |  2     |  reserve    |
      |  3     |  reserve    |
      |  4     |  no reserve |
      |  5     |  no reserve |

  Scenario Outline: Send motivational reminders to clients with low progress successfully
    Given I have a list of clients with progress data
    And a client's completion rate is below "30"
    When I send motivational reminders to the client
    Then the client "<client>"  shouled reserve the "<reminder>" and the system display "Motivational Reminder has been sent"
    Examples:
      | client | reminder    |
      |  1     |  no reserve |
      |  2     |  no reserve |
      |  3     |  reserve    |
      |  4     |  no reserve |
      |  5     |  no reserve |

  Scenario Outline: Send motivational reminders to clients with low progress successfully
    Given I have a list of clients with progress data
    And a client's completion rate is below "70"
    When I send motivational reminders to the client
    Then the client "<client>"  shouled reserve the "<reminder>" and the system display "Motivational Reminder has been sent"
    Examples:
      | client | reminder    |
      |  1     |  reserve    |
      |  2     |  reserve    |
      |  3     |  reserve    |
      |  4     |  no reserve |
      |  5     |  reserve    |
  Scenario: Send motivational reminders to clients with wrong limit format
    Given I have a list of clients with progress data
    And a client's completion rate is below "ff"
    When I send motivational reminders to the client
    Then the system display "wrong format of limit"

  Scenario: Send motivational reminders to clients with wrong limit format
    Given I have a list of clients with progress data
    And a client's completion rate is below "-50"
    When I send motivational reminders to the client
    Then the system display "wrong format of limit"

  Scenario: Send motivational reminders to clients with wrong limit format
    Given I have a list of clients with progress data
    And a client's completion rate is below "150"
    When I send motivational reminders to the client
    Then the system display "wrong format of limit"