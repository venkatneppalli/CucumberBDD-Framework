Feature: Verify login hotel app

  @tag1
  Scenario: test valid credentails
    Given user navigate to with "fourmoduleAdmin" role
    When I enter correct userid and password
    Then I validate logout link

