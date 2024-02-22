package se.attafemton.personal.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.Convert;
import se.attafemton.personal.UuidToBinaryConverter;

import java.util.Date;
import java.util.UUID;

@Entity
public class ImportantDate {

    @Id
    @Column(columnDefinition="BINARY(16)")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator",
            parameters = { @Parameter(name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.StandardRandomStrategy") })
    @Convert(converter = UuidToBinaryConverter.class)
    private UUID id;
    private DateType type;
    private Date date;
    private DateFormat format;

    public enum DateType {
        BIRTHDAY, WEDDING_DAY;
    }

    public enum DateFormat {
        DAY, DAY_TIME, TIME;
    }

    public ImportantDate() {
    }

    public ImportantDate(DateType type, Date date, DateFormat format) {
        this.type = type;
        this.date = date;
        this.format = format;
    }

    public UUID getId() {
        return id;
    }

    public DateType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public DateFormat getFormat() {
        return format;
    }
}