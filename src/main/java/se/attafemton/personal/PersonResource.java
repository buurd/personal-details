package se.attafemton.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.attafemton.personal.model.Person;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable UUID id) {
        return personRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @PostMapping
    public Person createPerson(@RequestBody Person newPerson) {
        return personRepository.save(newPerson);
    }

    @PutMapping("/{id}") // Corrected method mapping
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @RequestBody Person personDetails) {
        Person updatedPerson = personRepository.save(personDetails);
        return ResponseEntity.ok(updatedPerson);
    }
}
