package se.attafemton.personal;

import jakarta.persistence.Converter;

import jakarta.persistence.AttributeConverter;
import java.nio.ByteBuffer;
import java.util.UUID;

@Converter(autoApply = true)
public class UuidToBinaryConverter implements AttributeConverter<UUID, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(UUID attribute) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(attribute.getMostSignificantBits());
        bb.putLong(attribute.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public UUID convertToEntityAttribute(byte[] dbData) {
        ByteBuffer bb = ByteBuffer.wrap(dbData);
        Long high = bb.getLong();
        Long low = bb.getLong();
        return new UUID(high, low);
    }
}