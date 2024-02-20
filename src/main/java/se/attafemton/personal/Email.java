package se.attafemton.personal;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Email {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator",
            parameters = { @Parameter(name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.StandardRandomStrategy") })
    private UUID id;
    private String email;

    @Enumerated(EnumType.STRING)
    private EmailType type;

    public enum EmailType {
        PERSONAL, WORK, ACADEMIC, OTHERS;
    }

    public Email() {
    }

    public Email(String email, EmailType type) {
        if (isValid(email)) {
            this.email = email;
            this.type = type;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pat = Pattern.compile(emailRegex);
        Matcher matcher = pat.matcher(email);
        return matcher.matches();
    }

    public String getEmail() {
        return email;
    }

    public EmailType getType() {
        return type;
    }
}
