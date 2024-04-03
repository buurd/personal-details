package se.attafemton.personal;

import java.util.UUID;

public class TokenInfo {
    private UUID token;

    // Constructor
    public TokenInfo(UUID token) {
        this.token = token;
    }

    public TokenInfo(){
        token = null;
    }

    public UUID getToken() {
        return token;
    }
}