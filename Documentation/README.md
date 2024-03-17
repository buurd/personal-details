# Personal Details
This application is called 'Personal Details'

It is supposed to allow a person to share it's details to other people or organisations but still to own the data. 

## Basic mechanisms
The basic mechanism of the sharing of data is the term lease. A lease has an expiration date and can always be revoked. 
 - Person A shares its address with another person B. Person B then gets a handle to a lease. Though that lease it can access the given details.
 - Person A shared its personal email address with a company for a year. The commpany gets a lease and can find the email. After a year the lease expires and the lease is void and can't access anything anymore.
   
## Domain
The system has a domain. Currently it has the following objects

### Bounded context of Person

#### Person (class)
 - id, UUID
 - name, String
 - surname, String
 - emails, List<Email>
 - socialMediaHandles, List<SocialMediaHandle>

#### Email (class)
 - id, UUID
 - email, String
 - emailType, EmailType

#### EmailType (enum)
 - Personal
 - Work
 - Academic
 - Other

#### ImportantDate (class)
 - type, String
 - date, Date
 - format, DateType

#### DateFormat (enum)
 - day
 - dayTime
 - time

#### DateType (enum)
 - Birthday
 - Weddingday

#### SocialMediaHandle (class)
 - id, UUID
 - platform, String
 - handle, String

#### SocialMediaPlatform (enum)
 - FACEBOOK
 - TWITTER
 - INSTAGRAM 
 - LINKEDIN
 - SNAPCHAT
 - TIKTOK
 - YOUTUBE
 - PINTEREST
 - REDDIT

### Bounded context of User Authentication

#### Account (class)
 - username, String

#### Token (class)
 - account
 - created
 - lastVisited
 - invalidated

# Uses cases
- As a user of the system i would like to register my personal details, such as 
    - name, 
    - adresses, 
    - email, 
    - handles to social media, 
    - important dates. 

- As a user of the system I would like to edit my personal details when they are subject to change.

- As a user of the system I would like to add and remove on all attributes that are lists in the Person-object.

- As a user of the system, I would like to add, edit, and remove multiple entries on all attributes that are lists (email addresses, important dates, and social media handles) in the Person object without encountering server errors.

- As a prospective user, I would like to register with a unique username. Upon successful registration, I would be directed to the start page.

- As a registered user, I would like to log into the system by providing my username. Upon successful login, a `Token` would be created for my session. This `Token` should be included in all my subsequent requests. My `Token's` lastVisited time should be updated with every authenticated request.

- As an authenticated user, I would like to log out from the system. Upon logging out, my session's `Token` should be invalidated, and I should be redirected to the login page.

- As a system, I would need to validate an incoming `Token` for all secured endpoints. The `Token` is valid if it is associated with a user, not invalidated, and optionally within a certain duration from its last visit time.

# Non functional requirements
 - The application shall not contain dependencies on deprecated dependencies. 
 - The application shall not contain dependencies with known security flaws. 
 - The system should appropriately handle and respond to errors arising from operations on list-type attributes (email addresses, important dates, and social media handles). In case of a server error, a clear error message should be provided, and the system should either offer guidance to resolve the issue or handle it automatically to ensure uninterrupted workflow.




