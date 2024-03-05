Given('I open the app at the root path', () => {
    cy.visit('http://localhost:8080');
});

Then('I should see "Welcome to our Application" as the title', () => {
    cy.get('h1').contains('Welcome to our Application').should('exist');
});

And('I should see the "View Persons" link', () => {
    cy.get('a').contains('View Persons').should('exist');
});