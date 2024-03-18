package se.attafemton.personal.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.*;
import se.attafemton.personal.UuidToBinaryConverter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Token {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.StandardRandomStrategy"
                    )
            }
    )
    @Convert(converter = UuidToBinaryConverter.class)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime lastVisited;

    @Column(nullable = false)
    private boolean invalidated;

    @PrePersist
    protected void onCreate() {
        this.created = LocalDateTime.now();
        this.lastVisited = LocalDateTime.now();
        this.invalidated = false;
    }

    // Added getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(LocalDateTime lastVisited) {
        this.lastVisited = lastVisited;
    }

    public boolean isInvalidated() {
        return invalidated;
    }

    public void setInvalidated(boolean invalidated) {
        this.invalidated = invalidated;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", account=" + account +
                ", created=" + created +
                ", lastVisited=" + lastVisited +
                ", invalidated=" + invalidated +
                '}';
    }

    public boolean isExpired(LocalDateTime verificationDate) {
        return lastVisited.isBefore(verificationDate.minusMinutes(5));
    }
}