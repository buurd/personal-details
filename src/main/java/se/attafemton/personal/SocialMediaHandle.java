package se.attafemton.personal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SocialMediaHandle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String platform;
    private String handle;

    // For JPA
    public SocialMediaHandle() {}

    public SocialMediaHandle(String platform, String handle) {
        this.platform = platform;
        this.handle = handle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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