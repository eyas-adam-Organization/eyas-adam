Feature: Notify clients about changes to program
  As a program manager
  I want to notify clients about updates or removals in programs
  So that they stay informed about relevant changes

  # Scenario for notifying clients enrolled in "program1" about an update
  Scenario Outline: Notify clients about updates to program1
    Given I have an "update" in program "program1"
    When I notify the clients enrolled in this program
    Then the client "<client>" should receive the notification "<notification>"
    And the system shows "The notification has been sent"
    Examples:
      | client | notification    |
      | 1      | update          |
      | 2      | update          |
      | 3      | update          |
      | 4      | update          |
      | 5      | no notification |
      | 6      | no notification |
      | 7      | no notification |
      | 8      | no notification |
      | 9      | no notification |
      | 10     | no notification |
      | 11     | no notification |
      | 12     | no notification |
      | 13     | no notification |

  # Scenario for notifying clients enrolled in "program2" about an update
  Scenario Outline: Notify clients about updates to program2
    Given I have an "update" in program "program2"
    When I notify the clients enrolled in this program
    Then the client "<client>" should receive the notification "<notification>"
    And the system shows "The notification has been sent"
    Examples:
      | client | notification    |
      | 1      | update          |
      | 2      | update          |
      | 3      | update          |
      | 4      | update          |
      | 5      | update          |
      | 6      | update          |
      | 7      | update          |
      | 8      | no notification |
      | 9      | no notification |
      | 10     | no notification |
      | 11     | no notification |
      | 12     | no notification |
      | 13     | no notification |

  # Scenario for notifying clients enrolled in "program3" about a removal
  Scenario Outline: Notify clients about removals from program3
    Given I have an "remove" in program "program3"
    When I notify the clients enrolled in this program
    Then the client "<client>" should receive the notification "<notification>"
    And the system shows "The notification has been sent"
    Examples:
      | client | notification    |
      | 1      | update          |
      | 2      | update          |
      | 3      | update          |
      | 4      | update          |
      | 5      | update          |
      | 6      | update          |
      | 7      | update          |
      | 8      | remove          |
      | 9      | remove          |
      | 10     | remove          |
      | 11     | no notification |
      | 12     | no notification |
      | 13     | no notification |

  # Scenario for notifying clients enrolled in "program1" about a removal
  Scenario Outline: Notify clients about removals from program1
    Given I have an "remove" in program "program1"
    When I notify the clients enrolled in this program
    Then the client "<client>" should receive the notification "<notification>"
    And the system shows "The notification has been sent"
    Examples:
      | client | notification    |
      | 1      | remove          |
      | 2      | remove          |
      | 3      | remove          |
      | 4      | remove          |
      | 5      | update          |
      | 6      | update          |
      | 7      | update          |
      | 8      | remove          |
      | 9      | remove          |
      | 10     | remove          |
      | 11     | no notification |
      | 12     | no notification |
      | 13     | no notification |

  Scenario: notify a program with no enrolled clients
    Given I have an "update" in program "program4"
    And no clients are enrolled in this program
    When I notify the clients enrolled in this program
    Then the system shows "No clients are enrolled in this program"

  Scenario: notify a program that did not have any change
    Given I have an "update" in program "program1"
    When I notify the clients enrolled in this program with no change
    Then the system shows "No change to this program"

  Scenario: notify a program that does not exist
    Given I have an "programNEW"
    When I notify for program that does not exist
    Then the system shows "this program does not exist"