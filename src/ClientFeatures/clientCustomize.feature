Feature: Customizing Client's profile data
  To let clients add more personal info about themselves to their account like age, weight, BMI, goal BMI, goal weight, preferences and restrictions


  Scenario: Client tried to update the value age to a non-numerical value
    Given the username is "adam"
    When client entered "age" "80" "28" "23" "72" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the age should be a numerical value
    Then send client to "change personal info" customize

  Scenario: Client tried to update the value age to a value outside the acceptable range
    Given the username is "adam"
    When client entered "5" "80" "28" "23" "72" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the age should be a value within the acceptable range
    Then send client to "change personal info" customize


  Scenario: Client tried to update the value weight to a non-numerical value
    Given the username is "adam"
    When client entered "25" "mass" "28" "23" "72" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the weight should be a numerical value
    Then send client to "change personal info" customize


  Scenario: Client tried to update the value weight to a value outside the acceptable range
    Given the username is "adam"
    When client entered "25" "999" "28" "23" "72" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the weight should be a value within the acceptable range
    Then send client to "change personal info" customize


  Scenario: Client tried to update the value BMI to a non-numerical value
    Given the username is "adam"
    When client entered "25" "80" "good enough BMI (LOL)" "23" "72" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the BMI should be a numerical value
    Then send client to "change personal info" customize


  Scenario: Client tried to update the value BMI to a value outside the acceptable range
    Given the username is "adam"
    When client entered "25" "80" "2" "23" "72" "diary-free food?" "well, diary" for the values respectively
    Then Then Tell the client that the BMI should be a value within the acceptable range
    Then send client to "change personal info" customize


  Scenario: Client tried to update the value goal BMI to a non-numerical value
    Given the username is "adam"
    When client entered "25" "80" "28" "good enough BMI (LOL)" "72" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the goal BMI should be a numerical value
    Then send client to "change personal info" customize


  Scenario: Client tried to update the value goal BMI to a value outside the acceptable range
    Given the username is "adam"
    When client entered "25" "80" "28" "2" "72" "diary-free food?" "well, diary" for the values respectively
    Then Then Tell the client that the goal BMI should be a value within the acceptable range
    Then send client to "change personal info" customize


  Scenario: Client tried to update the value goal weight to a non-numerical value
    Given the username is "adam"
    When client entered "25" "80" "28" "23" "thin" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the goal weight should be a numerical value
    Then send client to "change personal info" customize


  Scenario: Client tried to update the value goal weight to a value outside than the acceptable range
    Given the username is "adam"
    When client entered "25" "80" "28" "23" "5" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the goal weight should be a value within the acceptable range
    Then send client to "change personal info" customize


  Scenario: Client tried to update with suitable values
    Given the username is "adam"
    When client entered "25" "80" "28" "23" "72" "diary-free food?" "well, diary" for the values respectively
    Then Tell the client that the update was successful
    Then send client to "main menu" customize
