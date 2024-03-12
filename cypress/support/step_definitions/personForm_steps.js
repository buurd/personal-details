const fillCorePersonDetails = (name, surname) => {
    cy.get('[data-testid="name-input"]').clear().type(name);
    cy.get('[data-testid="surname-input"]').clear().type(surname);
    cy.get('[data-testid="add-email-button"]').click();
    cy.get('[data-testid="email-input-0"]').type('test@email.com');
};

Given('I am on the person form page with a blank form', () => {
    cy.visit('http://localhost:8080/personForm/new', { timeout: 10000 });
    cy.get('[data-testid="person-form"]').should('be.visible');
});

When('I fill in all required fields', () => {
    fillCorePersonDetails('Test Name', 'Test Surname');
    cy.get('[data-testid="email-select-0"]').select('PERSONAL');
});

Then('I submit the form', () => {
    cy.intercept('POST', 'http://localhost:8080/persons').as('postCheck');
    cy.get('[data-testid="submit-button"]').should('be.visible').click()
    cy.wait('@postCheck').its('response.statusCode').should('eq', 200);


});

Then('I should be redirected to the person view page', () => {
    cy.window().then((win) => {
        expect(win.location.pathname).to.include('/personView/');
    });
});
Then('I should be redirected to the person view page', () => {
    cy.url().should('include', '/personView/');
});

Then('the new person\'s details should be saved correctly', () => {
    cy.get('[data-testid="person-heading"]')
        .should('have.text', `Test Name Test Surname`);

    // Check the email
    cy.get('[data-testid="email-0"]')
        .should('have.text', 'test@email.com (personal)');
});

Then('I should be redirected to the person view page', () => {
    cy.url().should('include', '/personView/');
});

Then('the new persons details should be saved correctly', () => {
});

When('I fill in the required fields', () => {
    fillCorePersonDetails('Test Name', 'Test Surname');
});

Then('I add multiple emails', () => {
    cy.get('[data-testid="add-email-button"]').click();
    cy.get('[data-testid="add-email-button"]').click();
    cy.get('[data-testid="email-input-1"]').type('test1@email.com');
    cy.get('[data-testid="email-input-2"]').type('test2@email.com');
    cy.get('[data-testid="email-select-1"]').select('WORK');
    cy.get('[data-testid="email-select-2"]').select('ACADEMIC');
});

Then('I remove one of the emails', () => {
    cy.get('[data-testid="remove-email-button-0"]').click();
});

Then('I should be able to submit the form successfully', () => {
    cy.get('[data-testid="submit-button"]').click();
});

// For each date in dates array
When('I add multiple important dates', () => {
    const dates = ['2022-04-01', '2022-05-01', '2022-06-01']; //flexible to add desired dates

    dates.forEach((date, index) => {
        // First Click add date button to generate new date field
        cy.get('[data-testid="add-date-button"]').click();

        // Then find the new date input field using its data-testid attribute and type the desired date
        cy.get(`[data-testid="date-input-${index}"]`).type(date);
    });
})

When('I remove one of the dates', () => {
    const indexToRemove = 1; // The index of the date field to remove, change as needed

    // Find the remove button for the specified date field and click it
    cy.get(`[data-testid="remove-date-button-${indexToRemove}"]`).click();
})

When('I add multiple social media handles', () => {
    const socialMediaHandles = [
        {platform: 'FACEBOOK', handle: 'test_handle_1'},
        {platform: 'TWITTER', handle: 'test_handle_2'},
        {platform: 'INSTAGRAM', handle: 'test_handle_3'}
        // Add more as required
    ];

    socialMediaHandles.forEach((socialMedia, index) => {
        // First, click the "Add Social Media" button
        cy.get('[data-testid="add-social-media-button"]').click();

        // Then select the platform from the dropdown
        cy.get(`[data-testid="platform-select-${index}"]`).select(socialMedia.platform);

        // Finally, type the handle into the handle input field
        cy.get(`[data-testid="handle-input-${index}"]`).type(socialMedia.handle);
    });
});

When('I remove one of the handles', () => {
    const indexToRemove = 0; // The index of the handle field to remove, change as needed

    // Find the remove button for the specific handle field and click it
    cy.get(`[data-testid="remove-social-media-button-${indexToRemove}"]`).click();
})

Then('I attempt to submit with missing required fields', () => {
    // Click the "Submit" button to try to submit the form
    cy.get(`[data-testid="submit-button"]`).click();
})

Then('I should be shown a clear validation message', () => {
    cy.on('window:alert', (str) => {
        expect(str).to.equal(`Please fill in all fields`)
    })
})