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

Given('the user is logged in with username {string}', (username) => {
    cy.request('POST', '/accounts/login', { username }) // send a request directly to the server
        .then((response) => {
            window.localStorage.setItem('token', response.body); // set the token in local storage
            cy.visit('/'); // go to the homepage
        });
});