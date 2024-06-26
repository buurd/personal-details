import { Given, When, Then } from "cypress-cucumber-preprocessor/steps";

Given("I am on the registration page", () => {
    cy.visit("/registration");
});

When("I enter a unique username", () => {
    cy.get("[data-testid=\"username-input\"]").type("UniqueUserName");
    cy.get("[data-testid=\"submit-button\"]").click();
});


Then(`I should be registered and redirected to the login page at {string}`, (url) => {
    cy.url().should('include', url); // assert that current URL should include '/login'
});

Given('a user with {string} exists', (username) => {
    cy.visit("/registration");
    cy.get("[data-testid=\"username-input\"]").type(username);
    cy.get("[data-testid=\"submit-button\"]").click();
    // Assuming a successful registration reroutes us back to the registration page
    cy.visit("/registration");
});

When("I try to register with {string}", (username) => {
    cy.get("[data-testid=\"username-input\"]").type(username);
    cy.get("[data-testid=\"submit-button\"]").click();
});

Then("I should see an error that the user already exists", () => {
    cy.get("[data-testid=\"error-message\"]").should('contain', 'User already exists');
});

Given("I am on the login page", () => {
    cy.visit("/login");
});

And("I have an account with {string}", (username) => {
    // We register a new user here for simplicity, consider separating user registration tasks in production
    cy.visit("/registration");
    cy.get("[data-testid=\"username-input\"]").type(username);
    cy.get("[data-testid=\"submit-button\"]").click();
    // Using '/login' as a redirection after successful registration
    cy.visit("/login");
});

When("I enter {string}", (username) => {
    cy.get("[data-testid=\"username-input\"]").type(username);
    // Depending on your login form, you might have a password field to fill in here too
    cy.get("[data-testid=\"submit-button\"]").click();
});

Then("I should receive a token in the response headers", () => {
    // Check for token in the response headers, adjust the property if needed
    cy.window().its('localStorage').invoke('getItem', 'token').should('exist');
});

And("I should be redirected to the start page at 'localhost:8080'", () => {
    cy.url().should("eq", "http://localhost:8080/");
});

Given("I login as {string}", (username) => {
    cy.visit('/login');
    cy.get('[data-testid="username-input"]').type(username);
    cy.get('[data-testid="submit-button"]').click();
});

Then("I should see message about already being looged in", () => {
    cy.get('[data-testid="error-message"]').contains('You are already logged in.');
});

And('The login button should be disabled', () => {
    cy.get('button[data-testid="submit-button"]').should('be.disabled');
});

And('I should be redirected to the start page after 5 seconds', () => {
    cy.wait(5000);
    cy.url().should("eq", "http://localhost:8080/");
});

Given("I am logged in with {string}", (username) => {
    // Assuming that a successful login ends on the index page
    cy.visit('/login');
    cy.get('[data-testid="username-input"]').type(username);
    cy.get('[data-testid="submit-button"]').click();
    cy.url().should("eq", "http://localhost:8080/");
});

When("I attempt to logout", () => {
    // Assuming that the logout button has an id of 'logout-button'
    cy.get('button[data-testid="logout-button"]').click();
});

Then("I should be redirected to the login page", () => {
    // This assumes that after logout, the user is redirected to the login page
    cy.url().should("eq", "http://localhost:8080/login");
});

And("No error message should be visible", () => {
    // This assumes that any errors, if they exist, have a data-testid of 'error-message'
    cy.get('[data-testid="error-message"]').should('not.exist');
})

When("I try to access the start page at {string}", (url) => {
    cy.visit(url);
});

Then("I should be redirected to the login page", () => {
    cy.url().should("eq", "http://localhost:8080/login");
});

Given('I am on the login page', () => {
    cy.visit('/login');
});

When('I click on the registration link', () => {
    cy.get('a[href="/registration"]').click();
});

Then('I should be redirected to the registration page', () => {
    cy.url().should('include', '/registration');
});