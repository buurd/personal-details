package se.attafemton.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.attafemton.personal.model.Account;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

    @Autowired
    private AccountRepository userRepository;

    @PostMapping
    public ResponseEntity<Account> createUser(@RequestBody Account newUser) {
        try {
            Account user = userRepository.save(newUser);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle potential exceptions, like duplicate usernames
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
