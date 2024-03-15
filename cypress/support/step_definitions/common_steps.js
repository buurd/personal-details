import { Given, When, Then } from "cypress-cucumber-preprocessor/steps";

Given('I am on the {string} page', (pagePath) => {
    cy.visit(`http://localhost:8080/${pagePath}`);
});

When('I click the {string} button', (buttonTestId) => {
    cy.get(`[data-testid="${buttonTestId}"]`).click();
});

Then('I should be redirected to the {string} page', (pagePath) => {
    cy.url().should('include', `${pagePath}`);
});