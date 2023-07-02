package si.zpiz.sample.infrastructure.book_related;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import si.zpiz.sample.domain.book_related.BookDbo;

public interface BookDboRepository extends JpaRepository<BookDbo, Long> {
    void deleteByUniqueId(UUID uniqueId);

    Optional<BookDbo> findByUniqueId(UUID uniqueId);

    // Supports wildcard searches
    // HQL
    @Query(value = "FROM BookDbo b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(b.publisher) LIKE LOWER(CONCAT('%', :value, '%'))", countQuery = "SELECT COUNT(b) FROM BookDbo b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :value, '%')) OR LOWER(b.publisher) LIKE LOWER(CONCAT('%', :value, '%'))")
    Page<BookDbo> findByStringsContainingIgnoreCase(@Param("value") String value, Pageable pageable);

    // Does not support wildcard searches
    Page<BookDbo> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
