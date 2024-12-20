Feature: create a new group.
  Scenario Outline: create a new group successfully
    Given i have instructor "<instructorID>" with schedules "<schedules>"  at time "<time>" and group id "<groupID>"
    When i create a new group
    Then the group will be add
    And the system should display a message for create "a new group has been created"
    Examples:
      | instructorID  | schedules   | time | groupID |
      | 1             | online      | 10   | 3       |
      | 2             | in-person   | 12   | 4       |
      | 3             | online      | 9    | 5       |


  Scenario Outline: create a new group with wrong group details
    Given i have instructor "<instructor>" with schedules "<schedules>"  at time "<time>" and group id "<groupID>"
    When i create a new group
    Then the group will not be add
    And the system should display a message for create "wrong data"
    Examples:
      | instructor  | schedules   | time |groupID |
      | 1           | -----c      | 10   | 6      |
      | 2           | in-person   | 26   | 7      |
      | 3           | online      | gg   | 8      |
      | 4           | onlines     | 26   | 9      |
      | 5           | onli        | rff  | 10     |
      | kk          | in-person   | 12   | 11     |


  Scenario: create a new group with to a instructor that does not exist
    Given i have instructor "12" with schedules "online"  at time "12" and group id "9"
    When i create a new group
    Then the group will not be add
    And the system should display a message for create "this instructor does not exist"

  Scenario: trying to create an existing group
    Given i have instructor "12" with schedules "online"  at time "2" and group id "1"
    When i create a new group
    And the system should display a message for create "this group already exist"