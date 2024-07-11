Feature: Editing an existing user

  Background:
    Given the user is logged in with username "username"

  Scenario: Edit an existing user
    Given I am on the "personList" page
    When I click on a person's details
    And I click the "edit-link" button
    Then I should be redirected to the "personForm" page

  Scenario: Save changes after editing an existing user
    Given I am editing an existing user in PersonForm
    When I change the user's details
    And I click the "submit-button" button
    Then the user's details should be updated
    And I should be redirected to the "personView" page

  Scenario: Cancel editing an existing user
    Given I am editing an existing user in PersonForm
    When I click the "cancel-button" button
    Then I should be redirected to the "personView" page
    And the user's details should not be changed