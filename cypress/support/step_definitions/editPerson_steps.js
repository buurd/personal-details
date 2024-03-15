import { Given, When, Then } from "cypress-cucumber-preprocessor/steps";

Given("I am on the PersonList page", () => {
    cy.visit("/personList");
});

When("I click on a person's details", () => {
    cy.get(".person-name").first().click();
    cy.url().should('include', `/personView/`);
});

And("I click on the Edit button", () => {
    cy.get("[data-testid=\"edit-link\"]") // replace with your actual selector
        .click();
});

Then("I should be redirected to the PersonForm with the person's details pre-filled", () => {
    cy.url().should("include", "/personForm");
    // Here you can also add checks to ensure that the form's fields are pre-filled
});

When("I change the user's details", () => {
    // assume you want to change the name of the person
    cy.get("[data-testid='name-input']").as('nameInput');
    cy.get('@nameInput').clear();
    cy.wait(500); // wait for the field to possibly update before typing the new value
    cy.get('@nameInput').type("New Name");
});

And("I click the Save button", () => {
    cy.get('[data-testid="submit-button"]').click();
});

Then("the user's details should be updated", () => {
    // Here you should add checks for the updated user's information in the list or on a specific detail page
});

And("I should be redirected to the PersonList page", () => {
    cy.url().should("include", "/personList");
});

When("I click the Cancel button", () => {
    // Click on the cancel button
    cy.get('[data-testid="cancel-button"]').click();

    // Check if it redirects correctly
    cy.url().should('contain', '/personView');
});

Then("I should be redirected to the PersonView page", () => {
    cy.url().should("include", "/personView");
});

And("the user's details should not be changed", () => {
    // Here you should check that the user's details were not changed.
});

Given('I am editing an existing user in PersonForm', () => {
    cy.visit('/personList');  // visit the person list page
    cy.get(".person-name").first().click();
    cy.url().should('include', `/personView/`);
    cy.get('[data-testid="edit-link"]').click();  // click the Edit button to go to the form
});