Feature: Person Form - Create New Person

  As a user
  I want to use the form specifically to add new people
  So that I can easily input and manage their details

  Background:
    Given the user is logged in with username "username"
    Given I am on the person form page with a blank form


  Scenario: Successfully add a new person
    When I fill in all required fields
    And I submit the form
    Then I should be redirected to the person view page
    And the new person's details should be saved correctly

  Scenario: Add and remove multiple emails
    When I fill in the required fields
    And I add multiple emails
    And I remove one of the emails
    Then I should be able to submit the form successfully

  Scenario: Add and remove multiple important dates
    When I fill in the required fields
    And I add multiple important dates
    And I remove one of the dates
    Then I should be able to submit the form successfully

  Scenario: Add and remove multiple social media handles
    When I fill in the required fields
    And I add multiple social media handles
    And I remove one of the handles
    Then I should be able to submit the form successfully

  Scenario: Validation prevents submission with missing required fields
    When I attempt to submit with missing required fields
    Then I should be shown a clear validation message



