Feature: Browse fitness programs by filters
  To easily look for a suitable program for the client

  Scenario: Client viewed the list for the first time or didn't use filter
    Given a list of programs
    When the client does not use a filter
    Then show all programs available

  Scenario: Client selected to filter by difficulty
    Given a list of programs
    When the client chooses "difficulty" "beginner"
    Then show programs related to "difficulty" "beginner"

  Scenario: Client selected to filter by focus area
    Given a list of programs
    When the client chooses "Focus Area" "Weight Loss"
    Then show programs related to "Focus Area" "Weight Loss"

  Scenario: Client selected to filter by difficulty and focus area
    Given a list of programs
    When the client chooses difficulty "beginner" and Focus Area "Weight Loss"
    Then show programs related to "beginner" and "Weight Loss"

  Scenario: Client chose to go back
    When the client choose to go back
    Then take the client to the main profile
