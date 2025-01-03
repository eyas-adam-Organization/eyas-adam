Feature: Assign clients to a group
  Scenario: Assign clients to a group successfully
    Given i have a client "9" and group "1"
    When i add the client to the group
    Then the client must be added
    And the system display the message "the client have been add to the group"

  Scenario: Assign clients to a group that he already assigned to
    Given i have a client "1" and group "1"
    When i add the client to the group
    Then the client will not be duplicated
    And the system display the message "the client is already in the group"

  Scenario: Assign clients to a group and the client id already assigned to another group
    Given i have a client "1" and group "2"
    When i add the client to the group
    Then the client will not be added
    And the system display the message "the client is assign to another group"

  Scenario: Assign clients to a group that does not exist
    Given i have a client "10" and group "7"
    When i add the client to the group
     And the system display the message "this group does not exist"

  Scenario: Assign a non-existing clients to a group
    Given i have a client "20" and group "1"
    When i add the client to the group
    Then the client will not be added
    And the system display the message "this client does not exist"