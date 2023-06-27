Feature: Petstore workshop pet

Background: Prerequisites
    Given I am a user of the petstore
    When I add a pet
    Then The pet is added

Scenario: Get pets by status
    When I look for pets by status sold
    #Then I see the list of pets with that status
    #When I look for pets by status available
    #Then I see the list of pets with that status
    #When I look for pets by status pending
    #Then I see the list of pets with that status 

@wip
Scenario: Get pet by id
    When I check the pet by its id
    Then The pet I added can be checked