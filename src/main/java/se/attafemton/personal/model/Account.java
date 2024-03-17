package se.attafemton.personal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Convert;
import se.attafemton.personal.UuidToBinaryConverter;
import java.util.UUID;

@Entity
public class User {

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

    @Column(unique = true, nullable = false)
    private String username;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String username) {
        setUsername(username);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}