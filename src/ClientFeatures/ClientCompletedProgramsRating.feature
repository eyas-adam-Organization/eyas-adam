Feature: Client program rating and reviewing
  Giving Clients the ability to rate and write reviews for programs that they already have completed...

  Scenario: Client tried to review a program that they did not complete
    When Client "eyas" gave a rating "9" and review "Very good! make me lose weight easily!" and suggestion "Could use more time per session." for program "Muscle Mastery"
    Then Tell the client that they need to complete the program first
    Then send client to "write a review"

  Scenario: Client entered a non-numerical value for rating
    When Client "eyas" gave a rating "good!" and review "Very good! make me lose weight easily!" and suggestion "Could use more time per session." for program "Weight Loss Advanced"
    Then Tell the client that the rating should be a numerical value
    Then send client to "write a review"

  Scenario: Client entered a value for rating outside the acceptable range
    When Client "eyas" gave a rating "-52" and review "VERY VERY BAD! MADE ME GAIN WEIGHT!" and suggestion "DO NOT FALL FOR THIS SCAM" for program "Weight Loss Advanced"
    Then Tell the client that the rating should be within the acceptable range
    Then send client to "write a review"

  Scenario: Client gave a bad rating and review, and wrote a good suggestion for the program
    When Client "eyas" gave a rating "1" and review "it's bad! didn't lose weight at all!" and suggestion "Time per session is too little! More session time can save the program." for program "Weight Loss Advanced"
    Then Apologize to the client and assure them that their suggestion will be considered
    Then send client to "main menu"

  Scenario: Client gave an okay rating and review, and wrote a good suggestion for the program
    When Client "eyas" gave a rating "5" and review "it's okay! didn't lose much weight though!" and suggestion "Needs more times per session!" for program "Weight Loss Advanced"
    Then Thank the client for the their review and say that you'll try to fix the problem
    Then send client to "main menu"

  Scenario: Client gave a good rating and review, and wrote a good suggestion for the program
    When Client "eyas" gave a rating "9" and review "Very good! made me lose weight easily!" and suggestion "Could use more time per session." for program "Weight Loss Advanced"
    Then Thank the client for the their liking of the program and the suggestion
    Then send client to "main menu"

  Scenario: Client chose to go back or entered an invalid input format
    When Client "eyas" entered "go back"
    Then send client to "main menu"