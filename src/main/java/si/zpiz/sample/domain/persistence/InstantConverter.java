package si.zpiz.sample.domain.persistence;

import java.sql.Timestamp;
import java.time.Instant;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class InstantConverter implements AttributeConverter<Instant, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(Instant instance) {
        if (instance == null) {
            return null;
        }
        return Timestamp.from(instance);
    }

    @Override
    public Instant convertToEntityAttribute(Timestamp dbData) {
        if (dbData == null) {
            return null;
        }
        return dbData.toInstant();
    }
}
