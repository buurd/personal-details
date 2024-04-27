package se.attafemton.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.attafemton.personal.model.Person;
import se.attafemton.personal.model.Token;

import java.time.LocalDateTime;
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

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable UUID id, @RequestHeader(name="Authorization") String token) {
        Optional<Token> optToken = tokenRepository.findById(UUID.fromString(token));
        if (optToken.isPresent() && !optToken.get().isExpired(LocalDateTime.now())) {
            Optional<Person> data = personRepository.findById(id);
            if (data.isPresent()) {
                return new ResponseEntity<>(data.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(@RequestHeader(name="Authorization") String token) {
        Optional<Token> optToken = tokenRepository.findById(UUID.fromString(token));

        if (optToken.isPresent() && !optToken.get().isExpired(LocalDateTime.now())) {
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
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person newPerson, @RequestHeader(name="Authorization") String token) {
        Optional<Token> optToken = tokenRepository.findById(UUID.fromString(token));

        if (optToken.isPresent() && !optToken.get().isExpired(LocalDateTime.now())) {
            newPerson.getImportantDates().forEach(importantDate -> importantDate.setId(null));
            newPerson.getEmails().forEach(email -> email.setId(null));
            newPerson.getSocialMediaHandles().forEach(socialMediaHandle -> socialMediaHandle.setId(null));

            Person person = personRepository.save(newPerson);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @RequestBody Person personDetails, @RequestHeader(name="Authorization") String token) {
        Optional<Token> optToken = tokenRepository.findById(UUID.fromString(token));

        if (optToken.isPresent() && !optToken.get().isExpired(LocalDateTime.now())) {
            personDetails.setId(id);
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
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
