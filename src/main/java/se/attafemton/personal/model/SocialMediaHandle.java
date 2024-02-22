package se.attafemton.personal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Convert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import se.attafemton.personal.UuidToBinaryConverter;

import java.util.UUID;

@Entity
public class SocialMediaHandle {

    @Id
    @Column(columnDefinition="BINARY(16)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator",
            parameters = { @Parameter(name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.StandardRandomStrategy") })
    @Convert(converter = UuidToBinaryConverter.class)
    private UUID id;
    private String platform;
    private String handle;

    // For JPA
    public SocialMediaHandle() {}

    public SocialMediaHandle(String platform, String handle) {
        this.platform = platform;
        this.handle = handle;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
}