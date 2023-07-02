package si.zpiz.sample.infrastructure.book_related.command_query;

import org.springframework.data.domain.Page;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.domain.misc.Paged;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBooksQuery implements IMediatorRequest<Page<BookDbo>> {
    /**
     * Must not be empty if mode is BY_TITLE_CONTAINING_IGNORE_CASE or
     * STRINGS_CONTAINING_IGNORE_CASE.
     */
    private String query;
    private GetBooksQueryMode mode;
    @Valid
    private Paged paged;
}
