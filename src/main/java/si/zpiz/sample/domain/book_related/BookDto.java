package si.zpiz.sample.domain.book_related;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import si.zpiz.sample.domain.author_related.AuthorDto;

@Data
@AllArgsConstructor
public class BookDto {
    @NonNull
    private String title;

    // Optional
    private AuthorDto author;

    private int year;

    @NonNull
    private String publisher;

    @NonNull
    private UUID uniqueId;
}
