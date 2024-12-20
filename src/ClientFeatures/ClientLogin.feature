Feature: Client login
  Self-explanatory
  Scenario: Client tried to sign in with a registered username and the correct password
    When Client tries to login using username "adam"  and password "Son_Goku_real"
    Then Tell the client that they were logged in successfully
    Then set the current registered user "adam"
    Then redirect client to "main menu"

  Scenario: Client tried to login using a not-registered username
    When Client tries to login using username "not_user"  and password "Son_Goku_real"
    Then Tell the client that they were not logged in successfully due to username issue
    Then redirect client to "sign out"

  Scenario: Client tried to login using the wrong password
    When Client tries to login using username "adam"  and password "666666666"
    Then Tell the client that they were not logged in successfully due to password issue
    Then redirect client to "sign out"


