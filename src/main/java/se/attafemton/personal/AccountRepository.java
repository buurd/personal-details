package se.attafemton.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import se.attafemton.personal.model.Account;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByUsername(String username);
}