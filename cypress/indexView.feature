Feature: Index View Page
  The Index View Page shows the title and a link to view persons

  Background:
    Given the user is logged in with username "testUser"

  Scenario: Loading the Index View Page
    Given I open the app at the root path
    Then I should see "Welcome to our Application" as the title
    And I should see the "View Persons" link