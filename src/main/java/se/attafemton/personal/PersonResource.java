package se.attafemton.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.attafemton.personal.model.Person;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable UUID id) {
        Optional<Person> data = personRepository.findById(id);

        if (data.isPresent()) {
            Person _person = data.get();

            return new ResponseEntity<>(_person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        try {
            List<Person> persons = new ArrayList<Person>();
            personRepository.findAll().forEach(persons::add);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public Person createPerson(@RequestBody Person newPerson) {
        // remove ids from embedded entities
        newPerson.getImportantDates().forEach(importantDate -> importantDate.setId(null));
        newPerson.getEmails().forEach(email -> email.setId(null));
        newPerson.getSocialMediaHandles().forEach(socialMediaHandle -> socialMediaHandle.setId(null));

        return personRepository.save(newPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @RequestBody Person personDetails) {
        personDetails.setId(id);

        // remove ids from embedded entities
        personDetails.getImportantDates().forEach(importantDate -> {
            if (importantDate.getId() != null && importantDate.getId().toString().equals("")) importantDate.setId(null);
        });
        personDetails.getEmails().forEach(email -> {
            if (email.getId() != null && email.getId().toString().equals("")) email.setId(null);
        });
        personDetails.getSocialMediaHandles().forEach(socialMediaHandle -> {
            if (socialMediaHandle.getId() != null && socialMediaHandle.getId().toString().equals("")) socialMediaHandle.setId(null);
        });

        Person updatedPerson = personRepository.save(personDetails);
        return ResponseEntity.ok(updatedPerson);
    }
}
