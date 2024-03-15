Feature: Editing an existing user

  Scenario: Edit an existing user
    Given I am on the PersonList page
    When I click on a person's details
    And I click on the Edit button
    Then I should be redirected to the PersonForm with the person's details pre-filled

  Scenario: Save changes after editing an existing user
    Given I am editing an existing user in PersonForm
    When I change the user's details
    And I click the Save button
    Then the user's details should be updated
    And I should be redirected to the PersonView page

  Scenario: Cancel editing an existing user
    Given I am editing an existing user in PersonForm
    When I click the Cancel button
    Then I should be redirected to the PersonView page
    And the user's details should not be changed