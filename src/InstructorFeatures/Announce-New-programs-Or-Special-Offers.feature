Feature: Announce new programs or special offers

  Scenario Outline: Announce an announcement successfully
    Given I have a list of clients and a new Announcement "<announcement>" "<value>"
    When I send Announcement reminders to the client
    Then the clients should reserve the Announcement
    And the system for announcement display "Announcement has been sent"

    Examples:
      | announcement | value      |
      | new program  | programNEW |
      | new offer    | offerNEW   |

  Scenario: Announce a new program that does not exist
    Given I have a list of clients and a new Announcement "new program" "programNotAdded"
    When I send Announcement a program thar does not exist
    Then the system for announcement display "this program does not exist"

  Scenario: wrong Announcement
    Given I have a list of clients and a new Announcement "mmm" "mmm"
    When I send Announcement reminders to the client
    Then the system for announcement display "wrong Announcement"

