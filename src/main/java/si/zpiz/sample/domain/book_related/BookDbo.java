package si.zpiz.sample.domain.book_related;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import si.zpiz.sample.domain.author_related.AuthorDbo;
import si.zpiz.sample.domain.persistence.DboBase;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class BookDbo extends DboBase {
    @Column(nullable = false)
    private String title;

    private int year;

    @Column(nullable = false)
    private String publisher;

    /* Deleting the author will result in FKs being set to null by the database */
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private AuthorDbo author;
}
