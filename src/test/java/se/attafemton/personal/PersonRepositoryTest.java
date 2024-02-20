package se.attafemton.personal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testCreateAndFindById() {
        Email email = new Email("john.doe@email.com", Email.EmailType.WORK);
        List<Email> emails = new ArrayList<>();
        emails.add(email);

        ImportantDate importantDate = new ImportantDate(ImportantDate.DateType.BIRTHDAY, new Date(), ImportantDate.DateFormat.DAY);
        List<ImportantDate> importantDates = new ArrayList<>();
        importantDates.add(importantDate);

        SocialMediaHandle socialMediaHandle = new SocialMediaHandle("Facebook", "john_doe");
        List<SocialMediaHandle> socialMediaHandles = new ArrayList<>();
        socialMediaHandles.add(socialMediaHandle);

        Person person = new Person("John", "Doe", emails, importantDates, socialMediaHandles);
        person = entityManager.persistAndFlush(person);

        Person found = personRepository.findById(person.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(person.getName());
        assertThat(found.getSurname()).isEqualTo(person.getSurname());
        assertThat(found.getEmails()).isEqualTo(person.getEmails());
        assertThat(found.getImportantDates()).isEqualTo(person.getImportantDates());
        assertThat(found.getSocialMediaHandles()).isEqualTo(person.getSocialMediaHandles());
        assertThat(found.getEmails().get(0).getType()).isEqualTo(email.getType());
    }

    @Test
    public void testFindAll() {
        Email email1 = new Email("john.doe@email.com", Email.EmailType.WORK);
        Email email2 = new Email("jane.doe@email.com", Email.EmailType.PERSONAL);
        List<Email> emails1 = new ArrayList<>();
        List<Email> emails2 = new ArrayList<>();
        emails1.add(email1);
        emails2.add(email2);

        ImportantDate importantDate1 = new ImportantDate(ImportantDate.DateType.BIRTHDAY, new Date(), ImportantDate.DateFormat.DAY);
        ImportantDate importantDate2 = new ImportantDate(ImportantDate.DateType.WEDDING_DAY, new Date(), ImportantDate.DateFormat.DAY_TIME);
        List<ImportantDate> importantDates1 = new ArrayList<>();
        List<ImportantDate> importantDates2 = new ArrayList<>();
        importantDates1.add(importantDate1);
        importantDates2.add(importantDate2);

        SocialMediaHandle socialMediaHandle1 = new SocialMediaHandle("Facebook", "john_doe");
        SocialMediaHandle socialMediaHandle2 = new SocialMediaHandle("Twitter", "jane_doe");
        List<SocialMediaHandle> socialMediaHandles1 = new ArrayList<>();
        List<SocialMediaHandle> socialMediaHandles2 = new ArrayList<>();
        socialMediaHandles1.add(socialMediaHandle1);
        socialMediaHandles2.add(socialMediaHandle2);

        Person person1 = new Person("John", "Doe", emails1, importantDates1, socialMediaHandles1);
        Person person2 = new Person("Jane", "Doe", emails2, importantDates2, socialMediaHandles2);

        entityManager.persistAndFlush(person1);
        entityManager.persistAndFlush(person2);

        List<Person> found = personRepository.findAll();
        assertThat(found).hasSize(2);
    }

    @Test
    public void testPersonWithManyEmails() {
        Email email1 = new Email("john.doe@work.com", Email.EmailType.WORK);
        Email email2 = new Email("john.doe@home.com", Email.EmailType.PERSONAL);
        List<Email> emails = new ArrayList<>();
        emails.add(email1);
        emails.add(email2);

        ImportantDate importantDate1 = new ImportantDate(ImportantDate.DateType.BIRTHDAY, new Date(), ImportantDate.DateFormat.DAY);
        ImportantDate importantDate2 = new ImportantDate(ImportantDate.DateType.WEDDING_DAY, new Date(), ImportantDate.DateFormat.DAY_TIME);
        List<ImportantDate> importantDates = new ArrayList<>();
        importantDates.add(importantDate1);
        importantDates.add(importantDate2);

        SocialMediaHandle socialMediaHandle1 = new SocialMediaHandle("Facebook", "john_doe");
        SocialMediaHandle socialMediaHandle2 = new SocialMediaHandle("Instagram", "john_doe");
        List<SocialMediaHandle> socialMediaHandles = new ArrayList<>();
        socialMediaHandles.add(socialMediaHandle1);
        socialMediaHandles.add(socialMediaHandle2);

        Person person = new Person("John", "Doe", emails, importantDates, socialMediaHandles);
        person = entityManager.persistAndFlush(person);

        Person found = personRepository.findById(person.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getEmails()).hasSize(2);
        assertThat(found.getEmails().get(0)).isEqualTo(email1);
        assertThat(found.getEmails().get(1)).isEqualTo(email2);
        assertThat(found.getImportantDates()).hasSize(2);
        assertThat(found.getImportantDates().get(0)).isEqualTo(importantDate1);
        assertThat(found.getImportantDates().get(1)).isEqualTo(importantDate2);
        assertThat(found.getSocialMediaHandles()).hasSize(2);
        assertThat(found.getSocialMediaHandles().get(0)).isEqualTo(socialMediaHandle1);
        assertThat(found.getSocialMediaHandles().get(1)).isEqualTo(socialMediaHandle2);
    }

    @Test
    public void testUpdatePerson() {
        // Create a person
        Person personA = new Person();
        personA.setName("Test Name");
        personA.setSurname("Test Surname");
        // Save it in database
        Person savedPerson = entityManager.persistAndFlush(personA);

        // Update the stored person
        savedPerson.setName("Updated Name");
        savedPerson.setSurname("Updated Surname");
        entityManager.persistAndFlush(savedPerson);

        // Retrieve the updated person from repository
        Optional<Person> updatedPersonOptional = personRepository.findById(savedPerson.getId());

        // Check if any person is returned
        Assertions.assertTrue(updatedPersonOptional.isPresent());

        // Check if values are updated
        Person updatedPerson = updatedPersonOptional.get();
        Assertions.assertEquals("Updated Name", updatedPerson.getName());
        Assertions.assertEquals("Updated Surname", updatedPerson.getSurname());
    }
}