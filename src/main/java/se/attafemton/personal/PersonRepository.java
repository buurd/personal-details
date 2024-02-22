package se.attafemton.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import se.attafemton.personal.model.Person;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    // No need for additional methods or fields
    // All basic CRUD operations are available by extending JpaRepository
}