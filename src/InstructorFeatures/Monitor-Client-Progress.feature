Feature: Monitor Client Progress

  Scenario Outline: Monitor client progress successfully
    Given I have a list of clients with progress data
    When I view the progress details for client "<ClientID>"
    Then I should see the completion rate "<CompletionRate>" and attendance record "<AttendanceRecord>"

    Examples:
      | ClientID | CompletionRate | AttendanceRecord |
      | 1        | 20             | 30               |
      | 2        | 50             | 40               |
      | 3        | 10             | 60               |
      | 4        | 90             | 10               |

  Scenario: Monitor a non-existing client progress
    Given I have a list of clients with progress data
    When I view the progress details for client "17"
    Then I should see a warning message "this client does not exist"

  Scenario: Monitor progress when a client has incomplete progress data
    Given I have a list of clients with incomplete progress data
    When I view the progress details for client "5"
    Then I should see a warning message "Progress data is incomplete for this client"

  Scenario: Monitor progress with invalid client ID format
    Given I have a list of clients with progress data
    When I view the progress details for client "abc"
    Then I should see a warning message "Invalid client ID format"

