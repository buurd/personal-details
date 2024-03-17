package se.attafemton.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import se.attafemton.personal.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // No need for additional methods or fields
    // JpaRepository provides you with basic CRUD operations like save(), findOne(), findAll(), count(), delete() etc.
}