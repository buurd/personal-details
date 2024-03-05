Feature: Visiting the Example Website

  Scenario: Basic Visit
    Given I open the Example website
    When I visit "https://example.cypress.io"
    Then I should see the title "Kitchen Sink" 