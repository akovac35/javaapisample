package si.zpiz.sample.infrastructure.book_related.command_query;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.zpiz.sample.infrastructure.mediator.IMediatorRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBookCommand implements IMediatorRequest<Void> {
    public UUID uniqueId;
}
