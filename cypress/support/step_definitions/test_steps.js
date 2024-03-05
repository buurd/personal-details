const { Given, When, Then } = require('cypress-cucumber-preprocessor/steps');

// Note: Replace the URL in the 'When' step if you want to test a different website
Given('I open the Example website', () => {
    cy.visit('https://example.cypress.io');
});

When('I visit {string}', (url) => {
    cy.visit(url);
});

Then('I should see the title {string}', (title) => {
    cy.title().should('include', title);
});
