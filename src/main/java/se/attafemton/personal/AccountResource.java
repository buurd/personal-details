package se.attafemton.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.attafemton.personal.model.Account;
import se.attafemton.personal.model.Token;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping
    public ResponseEntity<Account> createUser(@RequestBody Account newUser) {
        try {
            Account user = accountRepository.save(newUser);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Account credentials) {
        Account foundAccount = accountRepository.findByUsername(credentials.getUsername());
        if (foundAccount != null) {
            Token token = tokenRepository.findLatestValidByAccountId(foundAccount.getId());
            // If no valid token exists or if the token has expired, create a new one
            if (token == null || token.isExpired(LocalDateTime.now())) {
                token = new Token();
                token.setAccount(foundAccount);
                tokenRepository.save(token);
            }
            return ResponseEntity.ok(token.getId().toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }

    @PostMapping("/validateToken")
    public ResponseEntity<String> validateToken(@RequestBody TokenInfo tokenInfo) {
        Optional<Token> token = tokenRepository.findById(tokenInfo.getToken());
        LocalDateTime now = LocalDateTime.now();
        if (token.isPresent()) {
            if (!token.get().isExpired(now)) {
                return ResponseEntity.ok("Token Valid.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Expired.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Invalid.");
        }
    }
}
