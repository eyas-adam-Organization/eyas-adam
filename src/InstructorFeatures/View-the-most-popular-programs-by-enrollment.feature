Feature: Display statistics for programs sorted by different attributes

  Scenario: Display the most popular programs sorted by enrollment
    Given the following program enrollment data:
      | programTitle | numOfClients |
      | program1     | 4            |
      | program2     | 3            |
      | program3     | 3            |
      | program4     | 0            |
      | program5     | 0            |
      | program6     | 0            |
      | program7     | 0            |
    When I request the programs sorted by enrollment
    Then the programs should be sorted in descending order of enrollments

  Scenario: Display the programs sorted by price
    Given the following program price data:
      | programTitle | price        |
      | program1     | 4            |
      | program2     | 3            |
      | program3     | 3            |
      | program4     | 0            |
      | program5     | 0            |
      | program6     | 0            |
      | program7     | 0            |
    When I request the programs sorted by price
    Then the programs should be sorted in descending order of price
