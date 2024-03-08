Feature: Person List
  As a user
  I want to see the list of persons
  So I can choose a person to view their details

  Background:
    Given a list of people is retrieved

  Scenario: Display person list
    When I go on the person list page
    Then I should see the list of persons

  Scenario: Click on a person
    Given I am viewing the list of persons
    When I click on a certain person
    Then I should navigate to that person's detail page

  Scenario: Click on Add new Person button
    Given I am viewing the list of persons
    When I click on the 'Add new Person' button
    Then I should navigate to the '/personForm' page