Feature: Generate reports on revenue, attendance, and client progress
  Scenario: generate the report for the clients progress successfully
    Given the data of the report with type "0" and path "src/main/resources/report.pdf" is as the following
    | clientID | completion | attendance|
    | 1        | 20         |  30       |
    | 2        | 50         |  40       |
    | 3        | 10         |  60       |
    | 4        | 90         |  10       |
    | 5        | 60         |  missed   |
    | 6        | 40         |  36       |
    | 7        | 50         |  39       |
    | 8        | 10         |  60       |
    | 9        | 90         |  9        |
    | 10       | 60         |  50       |
    | 11       | 10         |  60       |
    | 12       | 90         |  9        |
    | 13       | 60         |  50       |
    When i generate of client progress
    Then the report is created successfully

  Scenario: generate the report for the revenue successfully
    Given the data of the report with type "1" and path "src/main/resources/report.pdf" is as the following
      | program   | number of clients    | price     |
      | program1  | 4                    | 35        |
      | program2  | 3                    | 20        |
      | program3  | 3                    | 12        |
      | program4  | 0                    | 20        |
      | program5  | 0                    | 40        |
      | program6  | 0                    | 50        |
      | program7  | 0                    | 10        |
    When i generate of client progress
    Then the report is created successfully
    And with total"236"





