# Editing Client Progress
Feature: Edit the progress of a client

  Scenario Outline: Edit progress data of a client successfully
    Given the type of the edited value is "<toEditType>"
    And the new value is "<NewValue>"
    When I edit the progress of client "<ClientID>"
    Then the system updates the progress data and displays a confirmation message "Progress updated successfully"
    Examples:
      | toEditType | NewValue | ClientID |
      | 1          | 40       | 1        |
      | 2          | 62       | 2        |
      | 3          | 40       | 1        |
      | 2          | 100      | 4        |
      | 1          | 40       | 5        |

  Scenario: Attempt to edit a non-existing client's progress
    Given I have a list of clients with progress data
    And the type of the edited value is "1"
    And the new value is "30"
    When I edit the progress of client "17"
    Then the system displays an error message "This client does not exist"

  Scenario Outline: Edit the progress of a client with invalid data
    Given the type of the edited value is "<toEditType>"
    And the new value is "<NewValue>"
    When I edit the progress of client "<ClientID>"
    Then the system displays an error message "<message>"
    Examples:
      | toEditType | NewValue | ClientID | message                                            |
      | 4          | 40       | 1        |  invalid type                                      |
      | 0          | 62       | 2        |  invalid type                                      |
      | -1         | 40       | 1        |  invalid type                                      |
      | fff        | 40       | 1        |  invalid type                                      |
      | 1          | -10      | 4        |  invalid data must be a positive number            |
      | 2          | -20      | 5        |  invalid data must be a positive number            |
      | 1          | bb       | 4        |  invalid data must be a positive number            |
      | 3          | -20      | 1        |  invalid data must be between 0 and 100            |
      | 3          | 150      | 5        |  invalid data must be between 0 and 100            |
      | 3          | vv       | 5        |  invalid data must be between 0 and 100            |
      | vv         | -40      | 1        |  invalid type                                      |
