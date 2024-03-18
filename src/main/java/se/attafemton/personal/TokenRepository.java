package se.attafemton.personal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.attafemton.personal.model.Token;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    @Query("FROM Token t WHERE t.account.id = :accountId AND t.invalidated = false ORDER BY t.created DESC")
    Token findLatestValidByAccountId(@Param("accountId") UUID accountId);
}
