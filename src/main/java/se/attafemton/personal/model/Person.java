package se.attafemton.personal.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Convert;
import se.attafemton.personal.UuidToBinaryConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Person {
    @Id
    @Column(columnDefinition="BINARY(16)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator",
            parameters = { @Parameter(name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.StandardRandomStrategy") })
    @Convert(converter = UuidToBinaryConverter.class)
    private UUID id;
    private String name;
    private String surname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImportantDate> importantDates;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SocialMediaHandle> socialMediaHandles;

    public Person() {
        this.emails = new ArrayList<>();
        this.importantDates = new ArrayList<>();
        this.socialMediaHandles = new ArrayList<>();
    }

    public Person(String name, String surname, List<Email> email, List<ImportantDate> importantDates, List<SocialMediaHandle> socialMediaHandles) {
        this();
        setName(name);
        setSurname(surname);
        setEmails(email);
        setImportantDates(importantDates);
        setSocialMediaHandles(socialMediaHandles);
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> email) {
        this.emails = email;
    }

    public void  setImportantDates(List<ImportantDate> importantDates){
        this.importantDates = importantDates;
    }

    public List<ImportantDate> getImportantDates(){
        return importantDates;
    }

    public List<SocialMediaHandle> getSocialMediaHandles() {
        return socialMediaHandles;
    }

    public void setSocialMediaHandles(List<SocialMediaHandle> socialMediaHandles) {
        this.socialMediaHandles = socialMediaHandles;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + emails + '\'' +
                ", importantDates='" + importantDates + '\'' +
                '}';
    }
}