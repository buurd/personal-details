package se.attafemton.personal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import se.attafemton.personal.model.Account;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountResourceIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setUsername("testUser");

        HttpEntity<Account> request = new HttpEntity<>(account);
        ResponseEntity<Account> response = restTemplate
                .exchange("/accounts", HttpMethod.POST, request, Account.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // assert that the username sent to the server is returned
        assertThat(response.getBody().getUsername()).isEqualTo(account.getUsername());

        // assert response id field is not null (as it should be assigned by the server)
        assertThat(response.getBody().getId()).isNotNull();
    }
}