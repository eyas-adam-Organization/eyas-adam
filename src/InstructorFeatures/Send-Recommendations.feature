Feature: Send recommendations
  # Sending Recommendations
  Scenario: Send recommendations to clients with high attendance but low progress
    Given I have clients with attendance above "80" and progress below "50"
    When I send program recommendations
    Then the system sends recommendations to improve progress and displays a confirmation message "Recommendations sent successfully"
