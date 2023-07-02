package si.zpiz.sample.infrastructure.author_related;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import si.zpiz.sample.domain.author_related.AuthorDbo;

public interface AuthorDboRepository extends JpaRepository<AuthorDbo, Long> {
    Optional<AuthorDbo> findByUniqueId(UUID uniqueId);

    void deleteByUniqueId(UUID uniqueId);
}
