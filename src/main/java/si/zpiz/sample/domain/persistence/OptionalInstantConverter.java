package si.zpiz.sample.domain.persistence;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OptionalInstantConverter implements AttributeConverter<Optional<Instant>, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(Optional<Instant> instance) {
        if (instance == null) {
            return null;
        }
        return instance.map(Timestamp::from).orElse(null);
    }

    @Override
    public Optional<Instant> convertToEntityAttribute(Timestamp dbData) {
        if (dbData == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(dbData).map(Timestamp::toInstant);
    }
}