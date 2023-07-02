package si.zpiz.sample.infrastructure.user_details_related;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import si.zpiz.sample.domain.user_details_related.UserDetailsDbo;

public interface UserDetailsDboRepository extends JpaRepository<UserDetailsDbo, Long> {
    Optional<UserDetailsDbo> findByUsernameIgnoreCase(String username);

    Optional<UserDetailsDbo> findByUniqueId(UUID uniqueId);
}
