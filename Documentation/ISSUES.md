Issues for Personal-details. 

| IssueNr | Type    | Reported   | Description | Solution | SolutionDate |
|       1 |  BUG    | 2024-02-25 | Create a new Person with 2 email-adresses. 
                         Then edit that person. 
                         Problem: Email-type is not selected
                         Expected: Email-type shoud be the same as when the person is created. | FIXED | 2024-02-25 |
|       2 |  BUG    | 2024-02-25 | Refreshed the browser and got an errormessage.
                         Problem: Can't reload page to see the same information.
                         Expected: Should come back to the same view as I was looking at.      | NONE |             |
|       3 |  BUG    | 2024-02-25 | Create a new Person with to dates
                         Then edit that person
                         Problem: Server error: detached entity passed to persist
                         Expected: To see the PersonView with changed attributes               | NONE |             |
|       4 | FEATURE | 2024-02-25 | Always present naviation to startpage
                                   Problem: Many click on back-button
                                   Expected: A link to startpage on every page easy available. | NONE  |            |