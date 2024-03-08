import { Given, When, Then } from 'cypress-cucumber-preprocessor/steps';

Given('a list of people is retrieved', () => {
    cy.visit('http://localhost:8080/personList').as('PersonList');
});

Given('I am viewing the list of persons', () => {
    cy.visit('http://localhost:8080/personList').as('PersonList');
});

When('I go on the person list page', () => {
    cy.visit('http://localhost:8080/personList');
});

Then('I should see the list of persons', () => {
    cy.get('@PersonList');
    cy.get('.person-item').should('have.length.greaterThan', 0);
});

When('I click on a certain person', () => {
    cy.get('.person-item').first().click();
});

Then('I should navigate to that person\'s detail page', () => {
    cy.location().should((loc) => {
        expect(loc.pathname).to.match(/^\/personView\/\w+/);
    });
});

When('I click on the {string} button', (buttonName) => {
    cy.contains('button', buttonName).click();
});

Then('I should navigate to the {string} page', page => {
    cy.url().should('include', page);
});