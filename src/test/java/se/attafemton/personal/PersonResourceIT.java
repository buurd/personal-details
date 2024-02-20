package se.attafemton.personal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonResourceIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreatePerson() {
        Email email = new Email("john.doe@example.com", Email.EmailType.PERSONAL);
        ImportantDate importantDate = new ImportantDate(ImportantDate.DateType.BIRTHDAY, new Date(), ImportantDate.DateFormat.DAY);
        SocialMediaHandle socialMediaHandle = new SocialMediaHandle("plattform", "handle");
        Person person = new Person("John", "Doe", Collections.singletonList(email), Collections.singletonList(importantDate), Collections.singletonList(socialMediaHandle));

        HttpEntity<Person> request = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate
                .exchange("/persons", HttpMethod.POST, request, Person.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // assert that the name and surname sent to the server are returned
        assertThat(response.getBody().getName()).isEqualTo(person.getName());
        assertThat(response.getBody().getSurname()).isEqualTo(person.getSurname());

        // assert that email fields sent to the server are returned
        assertThat(response.getBody().getEmails().get(0).getType())
                .isEqualTo(person.getEmails().get(0).getType());
        assertThat(response.getBody().getEmails().get(0).getEmail())
                .isEqualTo(person.getEmails().get(0).getEmail());

        // assert that date fields sent to the server are returned
        assertThat(response.getBody().getImportantDates().get(0).getType())
                .isEqualTo(person.getImportantDates().get(0).getType());
        assertThat(response.getBody().getImportantDates().get(0).getDate())
                .isEqualTo(person.getImportantDates().get(0).getDate());
        assertThat(response.getBody().getImportantDates().get(0).getFormat())
                .isEqualTo(person.getImportantDates().get(0).getFormat());

        // assert response id field is not null (as it should be assigned by the server)
        assertThat(response.getBody().getId()).isNotNull();
    }

    @Test
    public void testCreatePersonWithTwoEmails() {
        Email email1 = new Email("john.doe@example.com", Email.EmailType.PERSONAL);
        Email email2 = new Email("j.doe@work.com", Email.EmailType.WORK);
        ImportantDate importantDate1 = new ImportantDate(ImportantDate.DateType.BIRTHDAY, new Date(), ImportantDate.DateFormat.DAY);
        ImportantDate importantDate2 = new ImportantDate(ImportantDate.DateType.WEDDING_DAY, new Date(), ImportantDate.DateFormat.DAY_TIME);
        SocialMediaHandle socialMediaHandle = new SocialMediaHandle("plattform", "handle");
        Person person = new Person("John", "Doe", Arrays.asList(email1, email2), Arrays.asList(importantDate1, importantDate2), Collections.singletonList(socialMediaHandle));

        HttpEntity<Person> request = new HttpEntity<>(person);
        ResponseEntity<Person> response = restTemplate
                .exchange("/persons", HttpMethod.POST, request, Person.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // assert that the name and surname sent to the server are returned
        assertThat(response.getBody().getName()).isEqualTo(person.getName());
        assertThat(response.getBody().getSurname()).isEqualTo(person.getSurname());

        // assert that first email fields sent to the server are returned
        assertThat(response.getBody().getEmails().get(0).getType())
                .isEqualTo(person.getEmails().get(0).getType());
        assertThat(response.getBody().getEmails().get(0).getEmail())
                .isEqualTo(person.getEmails().get(0).getEmail());

        // assert that second email fields sent to the server are returned
        assertThat(response.getBody().getEmails().get(1).getType())
                .isEqualTo(person.getEmails().get(1).getType());
        assertThat(response.getBody().getEmails().get(1).getEmail())
                .isEqualTo(person.getEmails().get(1).getEmail());

        // assert that first date fields sent to the server are returned
        assertThat(response.getBody().getImportantDates().get(0).getType())
                .isEqualTo(person.getImportantDates().get(0).getType());
        assertThat(response.getBody().getImportantDates().get(0).getDate())
                .isEqualTo(person.getImportantDates().get(0).getDate());
        assertThat(response.getBody().getImportantDates().get(0).getFormat())
                .isEqualTo(person.getImportantDates().get(0).getFormat());

        // assert that second date fields sent to the server are returned
        assertThat(response.getBody().getImportantDates().get(1).getType())
                .isEqualTo(person.getImportantDates().get(1).getType());
        assertThat(response.getBody().getImportantDates().get(1).getDate())
                .isEqualTo(person.getImportantDates().get(1).getDate());
        assertThat(response.getBody().getImportantDates().get(1).getFormat())
                .isEqualTo(person.getImportantDates().get(1).getFormat());

        // assert response id field is not null (as it should be assigned by the server)
        assertThat(response.getBody().getId()).isNotNull();
    }

    @Test
    public void testUpdatePerson() {
        // Create a new person
        Person newPerson = new Person();
        newPerson.setName("Test name");
        newPerson.setSurname("Test surname");

        // POST new person to create it
        HttpEntity<Person> newPersonEntity = new HttpEntity<>(newPerson);
        ResponseEntity<Person> createdPersonResponse = restTemplate.postForEntity(
                "/persons",
                newPersonEntity,
                Person.class);

        Assertions.assertEquals(HttpStatus.OK, createdPersonResponse.getStatusCode());
        Person createdPerson = createdPersonResponse.getBody();

        // Prepare updated person details
        Person updatedPerson = new Person();
        updatedPerson.setId(createdPerson.getId());
        updatedPerson.setName("Updated name");
        updatedPerson.setSurname("Updated surname");

        // Create HTTP entity with updated person
        HttpEntity<Person> updatedPersonEntity = new HttpEntity<>(updatedPerson);

        // Send PUT request to update the person
        ResponseEntity<Person> updatedPersonResponse = restTemplate.exchange(
                "/persons/" + updatedPerson.getId(),
                HttpMethod.PUT,
                updatedPersonEntity,
                Person.class);

        // Verify response status
        Assertions.assertEquals(HttpStatus.OK, updatedPersonResponse.getStatusCode());

        // Verify the updated person
        Person responseBody = updatedPersonResponse.getBody();
        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals("Updated name", responseBody.getName());
        Assertions.assertEquals("Updated surname", responseBody.getSurname());
    }
}