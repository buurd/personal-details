package se.attafemton.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import se.attafemton.personal.model.Account;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    // No need for additional methods or fields
    // JpaRepository provides you with basic CRUD operations like save(), findOne(), findAll(), count(), delete() etc.
}