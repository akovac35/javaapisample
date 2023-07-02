package si.zpiz.sample.infrastructure.book_related.command_query;

import java.util.Optional;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookQuery implements IMediatorRequest<Optional<BookDbo>> {
    @NotNull
    private UUID uniqueId;
}
