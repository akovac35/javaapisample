package si.zpiz.sample.domain.persistence;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class DboBase implements IDboBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true)
    @Convert(converter = UUIDConverter.class)
    protected UUID uniqueId = UUID.randomUUID();

    @Version
    protected int rowVersion;

    @Column(nullable = false)
    protected Instant createdOnUtc = Instant.now();

    /* Example converter usage */
    @Column(nullable = true)
    @Convert(converter = OptionalInstantConverter.class)
    protected Optional<Instant> modifiedOnUtc = Optional.empty();

    @PreUpdate
    public void onSavingModified() {
        rowVersion += 1;
        modifiedOnUtc = Optional.of(Instant.now());
    }
}
