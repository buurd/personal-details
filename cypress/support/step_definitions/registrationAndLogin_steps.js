import { Given, When, Then } from "cypress-cucumber-preprocessor/steps";

Given("I am on the registration page", () => {
    cy.visit("/registration");
});

When("I enter a unique username", () => {
    cy.get("[data-testid=\"username-input\"]").type("UniqueUserName");
    cy.get("[data-testid=\"submit-button\"]").click();
});

Then("I should be registered and redirected to the start page at 'localhost:8080'", () => {
    cy.url().should("eq", "http://localhost:8080/");
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