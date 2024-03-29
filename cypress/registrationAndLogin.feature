Feature: User Registration and Login

Scenario: Register new user
   Given I am on the registration page
   When  I enter a unique username
   Then  I should be registered and redirected to the start page at 'localhost:8080'

Scenario: Register a duplicate username
   Given I am on the registration page
   And  a user with 'username' exists
   When I try to register with 'username'
   Then I should see an error that the user already exists

Scenario: Login with registered username
   Given I am on the login page
   And  I have an account with 'username'
   When I enter 'username'
   Then  I should be redirected to the start page at 'localhost:8080'
   And I should receive a token in the response headers


# Scenario: Login with already logged in user
#    Given I am on the login page
#    And  I have a valid token for 'username'
#    When I enter 'username'
#    Then I should be redirected to the start page at 'localhost:8080'

# Scenario: Logout from the application
#    Given I am logged in with 'username'
#    And  I have a valid token for 'username'
#    When I attempt to logout
#    Then my token should be invalidated
#    And  I should be redirected to the login page

# Scenario: Accessing the start page without logging in
#    Given I am not logged in
#    When  I try to access the start page at 'localhost:8080'
#    Then I should be redirected to the login page

# Scenario: Accessing the registration page from the login page
#    Given I am on the login page
#    When  I click on the registration link
#    Then I should be redirected to the registration page