Feature: Display statistics for the most popular programs by enrollment

  Scenario: Display the most popular programs sorted "byEnrollment" data
    Given the following program enrollment data:
      | programTitle | numOfClients |
      | program1     | 4           |
      | program2     | 3           |
      | program3     | 3           |
      | program4     | 0           |
      | program5     | 0           |
      | program6     | 0           |
      | program7     | 0           |

    When I request the most popular programs by enrollment

    Then the programs should be sorted in descending order of enrollments

    And the first program in the list should have 4 enrollments
    And the second program in the list should have 3 enrollments
    And the third program in the list should have 3 enrollments
    And the last program in the list should have 0 enrollments

  Scenario: Display the programs sorted "byPrice" data
    Given the following program price data:
      | programTitle | price       |
      | program1     | 4           |
      | program2     | 3           |
      | program3     | 3           |
      | program4     | 0           |
      | program5     | 0           |
      | program6     | 0           |
      | program7     | 0           |

    When I request the most popular programs by enrollment

    Then the programs should be sorted in descending order of enrollments

    And the first program in the list should have 4 enrollments
    And the second program in the list should have 3 enrollments
    And the third program in the list should have 3 enrollments
    And the last program in the list should have 0 enrollments



