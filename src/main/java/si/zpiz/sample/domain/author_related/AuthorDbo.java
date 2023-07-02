package si.zpiz.sample.domain.author_related;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import si.zpiz.sample.domain.persistence.DboBase;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "Authors")
public class AuthorDbo extends DboBase {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
}
