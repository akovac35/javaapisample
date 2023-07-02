package si.zpiz.sample.infrastructure.book_related.command_query;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.domain.book_related.BookDbo;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookCommand implements IMediatorRequest<BookDbo> {
    @NotBlank
    @Size(min = 5, max = 20)
    private String title;

    @Min(2000)
    private int year;

    @NotBlank
    @Size(min = 5, max = 20)
    private String publisher;

    // Optional
    private UUID authorUniqueId;

    @NotNull
    private UUID uniqueId;
}
