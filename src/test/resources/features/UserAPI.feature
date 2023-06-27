Feature: Petstore workshop user

Background: Prerequisites
    Given I am a user of the petstore
    When I add a new user
    Then The user is added
    Then I check the info for the user
    Then The info is correct

@wip
Scenario: Update the user
    When I update the created user
    Then The user is updated
    Then I check the info for the user
    Then The info is correct

@wip
Scenario: Delete the user
    When I delete the created user
    Then The user is deleted

