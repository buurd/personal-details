# Personal Details
This application is called 'Personal Details'

It is supposed to allow a person to share it's details to other people or organisations but still to own the data. 

## Basic mechanisms
The basic mechanism of the sharing of data is the term lease. A lease has an expiration date and can always be revoked. 
 - Person A shares its address with another person B. Person B then gets a handle to a lease. Though that lease it can access the given details.
 - Person A shared its personal email address with a company for a year. The commpany gets a lease and can find the email. After a year the lease expires and the lease is void and can't access anything anymore.
   
## Domain
The system has a domain. Currently it has the following objects

### Person (class)
 - id, UUID
 - name, String
 - surname, String
 - emails, List<Email>
 - socialMediaHandles, List<SocialMediaHandle>

### Email (class)
 - id, UUID
 - email, String
 - emailType, EmailType

### EmailType (enum)
 - Personal
 - Work
 - Academic
 - Other

### ImportantDate (class)
 - type, String
 - date, Date
 - format, DateType

### DateFormat (enum)
 - day
 - dayTime
 - time

### DateType (enum)
 - Birthday
 - Weddingday

### SocialMediaHandle (class)
 - id, UUID
 - platform, String
 - handle, String

### SocialMediaPlatform (enum)
 - FACEBOOK
 - TWITTER
 - INSTAGRAM 
 - LINKEDIN
 - SNAPCHAT
 - TIKTOK
 - YOUTUBE
 - PINTEREST
 - REDDIT

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

# Non functional requirements
 - The application shall not contain dependencies on deprecated dependencies. 
 - The application shall not contain dependencies with known security flaws. 
 - The system should appropriately handle and respond to errors arising from operations on list-type attributes (email addresses, important dates, and social media handles). In case of a server error, a clear error message should be provided, and the system should either offer guidance to resolve the issue or handle it automatically to ensure uninterrupted workflow.




